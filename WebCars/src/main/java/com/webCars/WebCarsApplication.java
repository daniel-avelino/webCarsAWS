package com.webCars;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.webCars.repositories.UserRepository;

@SpringBootApplication
public class WebCarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCarsApplication.class, args);
	}

}
