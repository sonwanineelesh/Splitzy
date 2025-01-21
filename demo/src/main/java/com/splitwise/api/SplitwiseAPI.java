package com.splitwise.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.DTO.UserDTO;
import com.splitwise.service.UserService;

@RestController
@CrossOrigin
@Validated
public class SplitwiseAPI {

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
		//TODO: process POST request
		String username = userService.register(userDto);
		if(username.equals(userDto.getUsername())) {
			System.out.println("Already exists");
		}
		return new ResponseEntity<>(username,HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
