package com.example.backend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.backend.entity.User;


@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findOneByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);

	User findByUsername(String username);

	Optional<User> findOneByUsernameAndPassword(String username, String encodedPassword);

}
