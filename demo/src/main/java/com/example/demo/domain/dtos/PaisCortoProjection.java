package com.example.demo.domain.dtos;

import org.springframework.data.rest.core.config.Projection;

import com.example.demo.domain.entities.Country;

@Projection(name = "PaisCorto", types = { Country.class }) 
public interface PaisCortoProjection {
	int getCountryId();
	String getCountry();
}
