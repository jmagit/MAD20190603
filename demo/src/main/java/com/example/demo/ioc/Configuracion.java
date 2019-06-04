package com.example.demo.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
	@Bean
	public Linea linea() {
		return new Linea();
	}

}
