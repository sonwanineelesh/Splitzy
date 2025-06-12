package com.splitwise.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.splitwise.entity.PasswordResetToken;
import com.splitwise.entity.Splitwise;
import com.splitwise.repository.ResetTokenRepository;
import com.splitwise.repository.UserRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
@Validated
public class TokenServiceIpml implements TokenService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Override
    public String forgotPassword(String email) {
        // TODO Auto-generated method stub
        Optional<Splitwise> user = userRepository.findByEmail(email);
	System.out.println(user.get().getEmail());
    if (!user.isPresent()) {
        return "User not found";
    }

    String token = UUID.randomUUID().toString();
    PasswordResetToken resetToken = new PasswordResetToken();
    resetToken.setToken(token);
    resetToken.setUser(user.get());
    resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

    resetTokenRepository.save(resetToken);

    return token;
    }
    
}
