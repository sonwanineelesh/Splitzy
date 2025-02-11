package com.splitwise.service;

import org.springframework.http.ResponseEntity;

import com.splitwise.DTO.UserDTO;

public interface UserService {

	String register(UserDTO userDto);
//	public UserDetails loadUserByUsername(UserDTO userDTO);

	ResponseEntity<?> authenticate(UserDTO loginRequest);
	}
