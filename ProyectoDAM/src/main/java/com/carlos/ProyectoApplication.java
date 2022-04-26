package com.carlos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // necesario para created at
public class ProyectoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}
}
