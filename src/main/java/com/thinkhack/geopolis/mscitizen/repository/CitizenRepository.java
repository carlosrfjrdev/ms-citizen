package com.thinkhack.geopolis.mscitizen.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinkhack.geopolis.mscitizen.model.CitizenModel;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenModel, UUID> {
	
	boolean existsByCitizenName(String citizenName);
	boolean existsByCitizenMail(String citizenMail);
	Optional<CitizenModel> findByCitizenName(String citizenName);
	
	
}
