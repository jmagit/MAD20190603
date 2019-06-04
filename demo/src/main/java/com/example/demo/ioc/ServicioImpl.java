package com.example.demo.ioc;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Service
public class ServicioImpl implements Servicio {
	@Override
	public String message() {
		return "Hola mundo";
	}
}
