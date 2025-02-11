package com.splitwise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.splitwise.DTO.UserPrincipal;
import com.splitwise.entity.Splitwise;
import com.splitwise.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class MyUserDetailsService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Splitwise> user = userRepository.findByEmail(username);
        if(user ==null) {
        	System.out.println("User not found");
        	throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());	}
}
