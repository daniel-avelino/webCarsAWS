package com.webCars.repositories;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.webCars.entities.User;

@EnableScan
public interface UserRepository extends CrudRepository<User, String>{

	User findByEmail(String email);

}
