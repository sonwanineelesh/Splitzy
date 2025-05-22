package com.splitwise.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.splitwise.DTO.UserDTO;

public interface UserService extends UserDetailsService {

	String register(UserDTO userDto);
//	public UserDetails loadUserByUsername(UserDTO userDTO);
public UserDetails loadUserByUsername(String email);
	ResponseEntity<?> authenticate(UserDTO loginRequest);
	}
