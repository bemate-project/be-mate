package com.bemate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BemateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BemateApplication.class, args);
	}

}
