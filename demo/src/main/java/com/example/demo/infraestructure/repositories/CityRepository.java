package com.example.demo.infraestructure.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.domain.entities.City;

@RepositoryRestResource(exported = false)
public interface CityRepository extends JpaRepository<City, Integer> {
	<T> List<T> findByCityIdNotNull(Class<T> type);
	List<City> findByCityStartingWithOrderByCityIdDesc(String prefijo);
	
}
