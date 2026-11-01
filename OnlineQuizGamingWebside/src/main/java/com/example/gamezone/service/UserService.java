package com.example.gamezone.service;


	import java.util.Optional;

//package com.example.gamezone.service;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;

	import com.example.gamezone.entity.User;
	import com.example.gamezone.repository.UserRepository;

	@Service
	public class UserService {

	    @Autowired
	    private UserRepository userRepository;
	    

	    public User registerUser(User user) {
	    	  if (user.getPassword() == null || user.getPassword().length() < 6) {
	    	        return null;
	    	    }

	        Optional<User> existingUser =
	                userRepository.findByEmail(user.getEmail());

	        if (existingUser.isPresent()) {
	            return null;
	        }

	        return userRepository.save(user);
	    }
	    

	    public User loginUser(String email, String password) {

	        Optional<User> optionalUser = userRepository.findByEmail(email);

	        if (optionalUser.isPresent()) {

	            User user = optionalUser.get();

	            if (user.getPassword().equals(password)) {
	                return user;
	            }
	        }

	        return null;
	    }
	}

