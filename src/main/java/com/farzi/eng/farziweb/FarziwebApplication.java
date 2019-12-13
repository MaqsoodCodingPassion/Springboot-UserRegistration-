package com.farzi.eng.farziweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@SpringBootApplication
@EnableScheduling
public class FarziwebApplication {
	public static void main(String[] args) {
		SpringApplication.run(FarziwebApplication.class, args);
	}
}

