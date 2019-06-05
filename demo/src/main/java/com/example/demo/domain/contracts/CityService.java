package com.example.demo.domain.contracts;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.domain.entities.City;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

public interface CityService {

	Set<ConstraintViolation<City>> validate(City item);

	boolean isValid(City item);

	boolean notIsValid(City item);

	List<City> getAll();

	Page<City> getAll(Pageable page);

	Optional<City> get(int id);

	City add(City item) throws InvalidDataException;

	City change(City item) throws InvalidDataException, NotFoundException;

	void delete(int id) throws InvalidDataException, NotFoundException;

	void delete(City item) throws InvalidDataException, NotFoundException;

}