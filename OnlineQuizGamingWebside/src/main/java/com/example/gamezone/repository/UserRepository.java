
	package com.example.gamezone.repository;

	import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
	import com.example.gamezone.entity.User;

	public interface UserRepository extends JpaRepository<User, Long> {
		
		Optional<User> findByEmail(String email);
	   // User findByEmail(String email);
	    
	}

