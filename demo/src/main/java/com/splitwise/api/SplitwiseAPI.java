package com.splitwise.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.splitwise.DTO.UserDTO;
import com.splitwise.entity.Splitwise;
import com.splitwise.repository.UserRepository;
import com.splitwise.service.UserService;
import com.splitwise.utility.JWTUtitlity;
//import com.splitwise.utility.JWTUtitlity;

@RestController
@CrossOrigin
@Validated
public class SplitwiseAPI {

//	@Autowired
//    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Environment environment;
	


	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserDTO userDto) {
		//TODO: process POST request
		String username = userService.register(userDto);
		if(username.equals(userDto.getEmail())) {
			System.out.println("Already exists");
		}
		return new ResponseEntity<>(username,HttpStatus.OK);
	}
	
	
	    @Autowired
	    private PasswordEncoder passwordEncoder;  // We'll configure this bean below

	    @Autowired
	    private JWTUtitlity jwtUtils;

	    @PostMapping("/login")
	    public ResponseEntity<?> authenticateUser(@RequestBody UserDTO loginRequest) {
	        Optional<Splitwise> userOptional = userRepository.findByEmail(loginRequest.getEmail());
	        if (userOptional.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body("Invalid Email or password");
	        }
	        Splitwise user = userOptional.get();
	        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body("Invalid email or password");
	        }
	        String token = jwtUtils.generateJwtToken(user.getEmail());
	        return ResponseEntity.ok(userService.authenticate(loginRequest));
	    }
	}

	
	
	

