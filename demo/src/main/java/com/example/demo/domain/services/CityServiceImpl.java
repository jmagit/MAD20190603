package com.example.demo.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.domain.contracts.CityService;
import com.example.demo.domain.entities.City;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.infraestructure.repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository dao;
	@Autowired
	@Transient
	private Validator validator;

	@Override
	public Set<ConstraintViolation<City>> validate(City item) {
		return validator.validate(item);
	}
	@Override
	public boolean isValid(City item) {
		return validate(item).size() == 0;
	}
	@Override
	public boolean notIsValid(City item) {
		return !isValid(item);
	}

	@Override
	public List<City> getAll() {
		return dao.findAll();
	}
	@Override
	public Page<City> getAll(Pageable page) {
		return dao.findAll(page);
	}
	@Override
	public Optional<City> get(int id) {
		return dao.findById(id);
	}
	
	@Transactional
	@Override
	public City add(City item) throws InvalidDataException {
		if(notIsValid(item))
			throw new InvalidDataException("Error de validacion de la entidad.");
		if(get(item.getCityId()).isPresent())
			throw new InvalidDataException("Clave duplicada.");
		return dao.save(item);
	}
	@Transactional
	@Override
	public City change(City item) throws InvalidDataException, NotFoundException {
		if(notIsValid(item))
			throw new InvalidDataException("Error de validacion de la entidad.");
		if(!get(item.getCityId()).isPresent())
			throw new NotFoundException();
		return dao.save(item);
	}
	@Override
	public void delete(int id) throws InvalidDataException, NotFoundException {
		if(!get(id).isPresent())
			throw new NotFoundException();
		dao.deleteById(id);
	}
	@Override
	public void delete(City item) throws InvalidDataException, NotFoundException {
		if(notIsValid(item))
			throw new InvalidDataException("Error de validacion de la entidad.");
		if(!get(item.getCityId()).isPresent())
			throw new NotFoundException();
		dao.deleteById(item.getCityId());
	}
	
}
