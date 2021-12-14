package com.babyboy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.babyboy.entity.User;
import com.babyboy.exception.ResourceNotFoundException;
import com.babyboy.repository.UserRepo;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		
		return new ResponseEntity<List<User>>(
				userRepo.findAll(), 
				HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user id is not found"));
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return  new ResponseEntity<User>(userRepo.save(user), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userUpdate) {
		
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("user id is not found"));
		
		user.setEmail(userUpdate.getEmail());
		user.setFirstName(userUpdate.getFirstName());
		user.setLastName(userUpdate.getLastName());
		
		return new ResponseEntity<User>(userRepo.save(user), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteUserById(@PathVariable long id) {
		
//		User user = userRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("user id is not found"));
//		
//		userRepo.delete(user);
		
		userRepo.deleteById(id);
		
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
}
