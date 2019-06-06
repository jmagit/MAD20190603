package com.example.demo.application.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.contracts.CityService;
import com.example.demo.domain.dtos.CityDTO;
import com.example.demo.domain.entities.Address;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Country;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.NotFoundException;

@RestController
@RequestMapping("/ciudades")
public class CityResource {
	@Autowired
	private CityService srv;

	@GetMapping
	public List<CityDTO> getAll() {
		return srv.getAll().stream()
				.map(item -> CityDTO.from(item))
				.collect(Collectors.toList());
	}
	@GetMapping(path = "/pagina")
	public Page<City> getAll(Pageable page) {
		return srv.getAll(page);
	}

	@GetMapping("/{id}")
	public CityDTO getOne(@PathVariable int id) throws NotFoundException {
		Optional<City> rslt = srv.get(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return CityDTO.from(rslt.get());
	}

	@Transactional
	@GetMapping("/{id}/direcciones")
	public List<Address> getDirecciones(@PathVariable int id) throws NotFoundException {
		Optional<City> rslt = srv.get(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get().getAddresses();
	}
	@Transactional
	@GetMapping("/{id}/pais")
	public Country getPais(@PathVariable int id) throws NotFoundException {
		Optional<City> rslt = srv.get(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return rslt.get().getCountry();
	}

	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody CityDTO source)
			throws BadRequestException, InvalidDataException {
		City item = CityDTO.from(source);
//		if (srv.notIsValid(item))
//			throw new BadRequestException("Datos invalidos");
		srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(item.getCityId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public CityDTO update(@PathVariable int id, @Valid @RequestBody CityDTO source) throws NotFoundException, InvalidDataException, BadRequestException {
		City item = CityDTO.from(source);
		if (srv.notIsValid(item))
			throw new InvalidDataException("Datos invalidos");
		if (id != item.getCityId())
			throw new BadRequestException("No coinciden los identificadores");
		item.setCityId(id);
		srv.change(item);
		return CityDTO.from(item);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) throws NotFoundException, InvalidDataException, BadRequestException {
		srv.delete(id);
	}

}
