package com.thinkhack.geopolis.mscitizen.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thinkhack.geopolis.mscitizen.enums.MessageType;
import com.thinkhack.geopolis.mscitizen.exception.NotEnoughEnergyException;
import com.thinkhack.geopolis.mscitizen.exception.NotEnoughGoldException;
import com.thinkhack.geopolis.mscitizen.exception.NotEnoughMoneyException;
import com.thinkhack.geopolis.mscitizen.exception.WrongEventException;
import com.thinkhack.geopolis.mscitizen.exception.WrongEventValueException;
import com.thinkhack.geopolis.mscitizen.model.CitizenModel;
import com.thinkhack.geopolis.mscitizen.service.EventService;
import com.thinkhack.geopolis.mscitizen.service.CitizenService;
import com.thinkhack.geopolis.mscitizen.to.MessageTO;
import com.thinkhack.geopolis.mscitizen.to.NewCitizenTO;
import com.thinkhack.geopolis.mscitizen.to.EventTO;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/citizen")
public class CitizenController {
	final CitizenService citizenService;

	public CitizenController(CitizenService citizenService) {
		this.citizenService = citizenService;
	}

	private final static long INITIAL_EXPERIENCE = 500L;
	private final static int INITIAL_SKILL = 50; 
	private final static int INITIAL_ENERGY = 50; 
	
	@PostMapping
	public ResponseEntity<Object> saveCitizen(@RequestBody @Valid NewCitizenTO newCitizenTO){
		boolean newCitizen = this.isNewCitizen(newCitizenTO);
		if(newCitizen) {
			var citizenModel = this.getNewCitizen(newCitizenTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(citizenService.save(citizenModel));
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageTO(MessageType.WARNING,"saveCitizen", "Conflict: Citizen Name is already in use!"));
		}
	}



	@GetMapping
	public ResponseEntity<Page<CitizenModel>> getAllCitizens(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.status(HttpStatus.OK).body(citizenService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneCitizen(@PathVariable(value = "id") UUID id){
		try {
			Optional<CitizenModel> citizenModelOptional = citizenService.findById(id);
			if (citizenModelOptional.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(citizenModelOptional.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageTO(MessageType.WARNING,"getOneCitizen", "Citizen not found."));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageTO(MessageType.WARNING,"getOneCitizen", "Wrong ID."));
		}
		

	}
	@GetMapping("/@{name}")
	public ResponseEntity<Object> getByCitizenName(@PathVariable(value = "name") String citizenName){
		Optional<CitizenModel> citizenModelOptional = citizenService.findByCitizenName(citizenName);
		if (citizenModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(citizenModelOptional.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageTO(MessageType.WARNING,"getByCitizenName", "Citizen not found."));
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCitizen(@PathVariable(value = "id") UUID id){

		Optional<CitizenModel> citizenModelOptional = citizenService.findById(id);

		if (citizenModelOptional.isPresent()) {
			citizenService.delete(citizenModelOptional.get());
			return ResponseEntity.status(HttpStatus.OK).body(new MessageTO(MessageType.INFO,"deleteCitizen", "Citizen deleted successfully."));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageTO(MessageType.WARNING,"deleteCitizen", "Citizen not found."));
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCitizenByEvent(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid EventTO event){

		Optional<CitizenModel> citizenModelOptional = citizenService.findById(id);
		EventService evt = new EventService();

		if (citizenModelOptional.isPresent()) {
			CitizenModel citizenModel;
			try {
				citizenModel = evt.getCitizenUpdateByEvent(citizenModelOptional.get(),event);

				evt.citizenEventListener(event,citizenModelOptional.get(),citizenModel);
				return ResponseEntity.status(HttpStatus.OK).body(citizenService.save(citizenModel));
			} catch (WrongEventException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageTO(MessageType.ERROR,"updateCitizenByEvent", "Incorrect Citizen event."));
			} catch (WrongEventValueException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageTO(MessageType.ERROR,"updateCitizenByEvent", "Incorrect value for event."));
			} catch (NotEnoughEnergyException e) {
				return ResponseEntity.status(HttpStatus.GONE).body(new MessageTO(MessageType.INFO,"updateCitizenByEvent", "Not Enough Energy For Citizen"));
			} catch (NotEnoughMoneyException e) {
				return ResponseEntity.status(HttpStatus.GONE).body(new MessageTO(MessageType.INFO,"updateCitizenByEvent", "Not Enough Money For Citizen"));
			} catch (NotEnoughGoldException e) {
				return ResponseEntity.status(HttpStatus.GONE).body(new MessageTO(MessageType.INFO,"updateCitizenByEvent", "Not Enough Gold For Citizen"));
			}


		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageTO(MessageType.WARNING,"updateCitizenByEvent", "Citizen not found."));
		}



	}

	//METODOS PRIVADOS



	private boolean isNewCitizen(NewCitizenTO citizenDTO) {

		return !citizenService.existsByCitizenName(citizenDTO.getCitizenName());
	}


	private CitizenModel getNewCitizen(NewCitizenTO citizenDTO) {
		CitizenModel citizenModel = new CitizenModel();
		BeanUtils.copyProperties(citizenDTO, citizenModel);
		citizenModel.setExperience(INITIAL_EXPERIENCE);
		citizenModel.setJobExperience(INITIAL_EXPERIENCE);
		citizenModel.setPoliticsExperience(INITIAL_EXPERIENCE);
		citizenModel.setWarExperience(INITIAL_EXPERIENCE);
		citizenModel.setPowerSkill(INITIAL_SKILL);
		citizenModel.setKnowledgeSkill(INITIAL_SKILL);
		citizenModel.setVip(false);
		citizenModel.setModerator(false);
		citizenModel.setBan(false);
		citizenModel.setEnergy(INITIAL_ENERGY);

		LocalDateTime dateNow = LocalDateTime.now(ZoneId.of("UTC"));

		citizenModel.setCreated(dateNow);
		citizenModel.setUpdated(dateNow);
		return citizenModel;
	}


	



}
