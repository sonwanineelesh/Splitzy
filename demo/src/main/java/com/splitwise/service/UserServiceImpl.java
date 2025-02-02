package com.splitwise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.splitwise.DTO.UserDTO;
import com.splitwise.entity.User;
import com.splitwise.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public String register(UserDTO userDTO) {
		// TODO Auto-generated method stub
		String registeredWithUsername = null;
		// check whether specified email id is already in use by other customer
		boolean isUsernameNotAvailable = userRepository.findById(userDTO.getUserId()).isEmpty();
		// check whether specified phone no. is already in use by other customer
		if (isUsernameNotAvailable) { 
				User user = new User();
				
//				user.setUserId(userDTO.getUserId());
				user.setEmail(userDTO.getEmail());
				user.setUsername(userDTO.getUsername());
				user.setPassword(userDTO.getPassword());
				System.out.println("mmmmmmmmmmmmmmmmmmm");
				userRepository.save(user);
				System.out.println("mmmmmmmmmmmmmmmmmmm");
				registeredWithUsername = user.getUsername();
			} 
		return registeredWithUsername;
	}
}
