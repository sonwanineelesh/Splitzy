package com.splitwise.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.splitwise.entity.Splitwise;

public interface UserRepository extends CrudRepository<Splitwise, Long>{

	Optional<Splitwise> findByEmail(String email);

//	Set<User> findByPayerEmail(String payerEmail);

//	String findById(Long userId);

}
