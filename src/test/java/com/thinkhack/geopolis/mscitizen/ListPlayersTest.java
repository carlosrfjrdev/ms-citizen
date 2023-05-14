package com.thinkhack.geopolis.mscitizen;

import java.util.List;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.thinkhack.geopolis.mscitizen.controller.CitizenController;
import com.thinkhack.geopolis.mscitizen.model.CitizenModel;
import com.thinkhack.geopolis.mscitizen.to.NewCitizenTO;

@SpringBootTest
class ListCitizensTest {

	@Autowired
	private CitizenController playerController;
	
	@Test
	void NewCitizenTest() {
	
		System.out.println("Insert");
		@Valid
		NewCitizenTO newCitizenTO = new NewCitizenTO();
		
		newCitizenTO.setCitizenName("Carlão");
		
		
		playerController.saveCitizen(newCitizenTO);
		
		
	}
	
	@Test
	void CitizenListTest() {
		System.out.println("list");
		
		
		ResponseEntity<Page<CitizenModel>> content = playerController.getAllCitizens(Pageable.unpaged());
		
		List<CitizenModel> players = content.getBody().getContent();
		
		for (CitizenModel playerModel : players) {
			
			System.out.println("getId: "+playerModel.getId());
			System.out.println("getCitizenName: "+playerModel.getCitizenName());
			System.out.println("getDescription: "+playerModel.getDescription());
			System.out.println("getLevel: "+playerModel.getLevel());
			System.out.println("getExperience: "+playerModel.getExperience());
			System.out.println("getPercXP: "+playerModel.getPercXP());
			System.out.println("getJobExperience: "+playerModel.getJobExperience());
			System.out.println("getWarExperience: "+playerModel.getWarExperience());
			System.out.println("getPoliticsExperience: "+playerModel.getPoliticsExperience());
			System.out.println("getPowerSkill: "+playerModel.getPowerSkill());
			System.out.println("getKnowledgeSkill: "+playerModel.getKnowledgeSkill());
			System.out.println("getKnowledgeSkill: "+playerModel.getEnergy());
			System.out.println("isVip: "+playerModel.isVip());
			System.out.println("getMoneyWallet: "+playerModel.getMoneyWallet());
			System.out.println("getGoldWallet: "+playerModel.getGoldWallet());
			System.out.println("getCreated: "+playerModel.getCreated());
			System.out.println("getUpdated: "+playerModel.getUpdated());
			System.out.println("===================================");
			
		}
		
		
	}

}
