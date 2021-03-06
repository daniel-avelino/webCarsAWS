package com.webCars.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webCars.entities.User;
import com.webCars.services.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<?> insertUser(@RequestBody User user) {
		service.save(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getUsers() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping(path = "/{email}")
	public ResponseEntity<?> getUsersByEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(service.findByName(email));
	}
}
