package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.app.models")
public class TinkinatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkinatorApplication.class, args);
	}

}
