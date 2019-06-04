package com.example.demo;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

import com.example.demo.domain.dtos.CityDTO;
import com.example.demo.domain.dtos.CityShortDTO;
import com.example.demo.infraestructure.repositories.CityRepository;
import com.example.demo.ioc.Linea;
import com.example.demo.ioc.Punto;
import com.example.demo.ioc.Servicio;

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
		dao.findByCityStartingWithOrderByCityIdDesc("A").forEach(item -> System.out.println(item));
	}

}
