package com.splitwise.repository;

import org.springframework.data.repository.CrudRepository;

import com.splitwise.entity.PasswordResetToken;


public interface ResetTokenRepository extends CrudRepository<PasswordResetToken, Long>{

    PasswordResetToken findByToken(String token);
    
}
