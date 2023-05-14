package com.thinkhack.geopolis.mscitizen.service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thinkhack.geopolis.mscitizen.model.CitizenModel;
import com.thinkhack.geopolis.mscitizen.repository.CitizenRepository;

@Service
public class CitizenService {

	final CitizenRepository citizenRepository;

	public CitizenService(CitizenRepository citizenRepository) {

		this.citizenRepository=citizenRepository;
	}


	@Transactional
	public CitizenModel save(CitizenModel citizenModel) {
		return citizenRepository.save(citizenModel);
	}

	
	public boolean existsByCitizenName(String citizenName) {
        return citizenRepository.existsByCitizenName(citizenName);
    }
	
	
	public boolean existsByCitizenMail(String citizenMail) {
        return citizenRepository.existsByCitizenMail(citizenMail);
    }

	public Page<CitizenModel> findAll(Pageable pageable) {
		return citizenRepository.findAll(pageable);
	}

	public Optional<CitizenModel> findById(UUID id) {
		return citizenRepository.findById(id);
	}
	public Optional<CitizenModel> findByCitizenName(String citizenName) {
		return citizenRepository.findByCitizenName(citizenName);
	}

	@Transactional
	public void delete(CitizenModel citizenModel) {
		citizenRepository.delete(citizenModel);
	}


}
