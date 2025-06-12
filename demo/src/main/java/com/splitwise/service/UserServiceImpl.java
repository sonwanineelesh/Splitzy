package com.splitwise.service;

import java.util.ArrayList;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.splitwise.DTO.AuthenticationResponse;
import com.splitwise.DTO.UserDTO;
import com.splitwise.entity.Splitwise;
import com.splitwise.repository.UserRepository;
import com.splitwise.utility.JWTUtitlity;

import jakarta.transaction.Transactional;

@Service
@Transactional
@Validated
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private JavaMailSender mailSender;
	
	// @Autowired
	// private AuthenticationManager authenticationManager;
//	@Autowired
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

 private final ObjectProvider<AuthenticationManager> authenticationManager;

    public UserServiceImpl(ObjectProvider<AuthenticationManager> authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	@Override
	public String register(UserDTO userDTO) {
		// TODO Auto-generated method stub
		String registeredWithUsername = null;
		// check whether specified email id is already in use by other customer
		boolean isUsernameNotAvailable = userRepository.findById(userDTO.getUserId()).isEmpty();
		// check whether specified phone no. is already in use by other customer
		if (isUsernameNotAvailable) { 
				Splitwise splitwise = new Splitwise();
				
//				user.setUserId(userDTO.getUserId());
				splitwise.setEmail(userDTO.getEmail());
				splitwise.setUsername(userDTO.getUsername());
				splitwise.setPassword(encoder.encode(userDTO.getPassword()));
				System.out.println(encoder.encode(userDTO.getPassword()));
				userRepository.save(splitwise);
				System.out.println("mmmmmmmmmmmmmmmmmmm");
				registeredWithUsername = splitwise.getUsername();
			} 
		return registeredWithUsername;
	}
	
	@Autowired
	private JWTUtitlity jwtUtitlity;
	
	@Override
	public ResponseEntity<?> authenticate(UserDTO loginRequest) {
		// TODO Auto-generated method stub
		authenticationManager.getObject().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
		String jwtToken = jwtUtitlity.generateJwtToken(user.getEmail());// changed
		
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
//		return ResponseEntity<String>();
		authenticationResponse.setToken(jwtToken);
		return ResponseEntity.ok(authenticationResponse);
	}
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Splitwise user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convert your Splitwise user to Spring Security's UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), new ArrayList<>()); // Empty authorities list
    }

	@Override
	public Long getYouOwed(String email) {
		// TODO Auto-generated method stub

		Splitwise user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));	
		
			return user.getBalance();
	}

	@Override
	public void sendPasswordResetEmail(String email, String token) {
		// TODO Auto-generated method stub
		String resetUrl = "https://localhost:8080/reset-password?token=" + token;
        String subject = "Reset Your Password";
        String body = "Click the link to reset your password:\n" + resetUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("your_email@example.com"); // Or from your SMTP config

        mailSender.send(message);
	}
}