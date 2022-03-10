package com.highschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class HighschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighschoolApplication.class, args);
	}

}
