package com.example.demo.ioc;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component
public class Linea {
	@Autowired
	@Qualifier("2d")
	private Punto p1, p2;
	
	public Linea() {
	}
	
	@PostConstruct
	private void init() {
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}

	@Override
	public String toString() {
		return "Linea [p1=" + p1 + ", p2=" + p2 + "]";
	}
	
}
