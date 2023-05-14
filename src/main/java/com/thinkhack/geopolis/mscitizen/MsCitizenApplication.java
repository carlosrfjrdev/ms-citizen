package com.thinkhack.geopolis.mscitizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@RestController
@OpenAPIDefinition(info = @Info(title = "Geopolis - Citizen Microservices", version = "0.1", description = "Citizen API"))

public class MsCitizenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCitizenApplication.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "GeoPolis API - Citizen Microservices - v0.1";
	}
}
