package com.webCars.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.webCars.entities.User;
import com.webCars.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	}

	public List<User> findAll() {
		List<User> users = (List<User>) repository.findAll();
		return users;
	}

	public User findByName(String email) {
		return repository.findByEmail(email);
	}
}
