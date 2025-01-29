package com.pragma.plazoleta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pragma"})
public class PlazoletaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlazoletaApplication.class, args);
	}

}
