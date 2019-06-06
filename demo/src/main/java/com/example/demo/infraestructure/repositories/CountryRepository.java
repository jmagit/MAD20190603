package com.example.demo.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.dtos.PaisCortoProjection;
import com.example.demo.domain.entities.Country;

@RepositoryRestResource(path="paises", itemResourceRel = "pais", collectionResourceRel="paises", excerptProjection = PaisCortoProjection.class)
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
