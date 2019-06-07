package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.contracts.CityService;
import com.example.demo.domain.dtos.CityDTO;
import com.example.demo.domain.dtos.CityShortDTO;
import com.example.demo.domain.dtos.FilmShortDTO;
import com.example.demo.domain.entities.City;
import com.example.demo.domain.entities.Country;
import com.example.demo.infraestructure.repositories.CityRepository;
import com.example.demo.ioc.Linea;
import com.example.demo.ioc.Punto;
import com.example.demo.ioc.Servicio;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	@Qualifier("2d")
	private Punto punto;
	@Autowired
	@Qualifier("3d")
	private Punto p2;
	@Value("${mi.valor}")
	private String valor;
	@Autowired
	private Servicio srv;
	@Autowired
	private Linea linea;
	
	@Autowired
	@Lazy
	private CityRepository dao;
	@Autowired
	private CityService srvCity;
	
	@Autowired
	private Validator validator;

	@Autowired 
	private RestTemplate srvRest;

	@Transactional
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(punto.toString());
//		punto.setX(100);
//		System.out.println(p2);
//		System.out.println(valor);
//		System.out.println(srv.message());
//		System.out.println(linea);
		
		//dao.findAll().forEach(item -> System.out.println(item));
//		dao.findAll().stream()
//			.map(item -> CityDTO.from(item))
//			.forEach(item -> System.out.println(item));
//		dao.findByCityIdNotNull(CityShortDTO.class).stream()
//			.forEach(item -> System.out.println(item.getCity()));
		// dao.findByCityStartingWithOrderByCityIdDesc("A").forEach(item -> System.out.println(item));
//			City city = new City(999, "", new Country(87));
//			Set<ConstraintViolation<City>> constraintViolations =  
//					validator.validate( city );
//			if(constraintViolations.size() > 0) {
//				System.out.println("Errores");
//				constraintViolations.forEach(item -> System.out.println(item.getMessage()));
//			} else {
//				dao.save(city);
//			}
//			if(city.isValid()) {
//				dao.save(city);
//			} else {
//				System.out.println("Errores");
//				city.validate().forEach(item -> System.out.println(item.getMessage()));
//				
//			}
//			dao.findAll().forEach(item -> System.out.println(item));
//			Optional<City> rslt = dao.findById(1);
//			if(rslt.isPresent()) {
//				System.out.println(rslt.get());
//				rslt.get().getAddresses()
//					.forEach(item -> System.out.println(item));
//			}
//		srvCity.delete(999);
//		srvCity.add(new City(999, "Madriddddd", new Country(87)));
//		srvCity.getAll().forEach(item -> System.out.println(item));
//		FilmShortDTO rslt = srvRest.getForObject( 
//				"http://localhost:8002/peliculas/{id}", FilmShortDTO.class, 1);
//		System.out.println("UNO: " + rslt.toString());
		ResponseEntity<List<FilmShortDTO>> response = 
				srvRest.exchange("http://localhost:8002/peliculas?mode=short", 
						HttpMethod.GET,
						HttpEntity.EMPTY, 
						new ParameterizedTypeReference<List<FilmShortDTO>>() {});
		List<FilmShortDTO> rslt = response.getBody();
		rslt.forEach(item -> System.out.println(item));
	}

}
