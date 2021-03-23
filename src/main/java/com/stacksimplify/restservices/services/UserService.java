package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exceptions.UserExistsException;
import com.stacksimplify.restservices.exceptions.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User exisringUser = userRepository.findByUserName(user.getUserName());
		if(exisringUser != null) {
			throw new UserExistsException("user already Exisits in repository !");
		}
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user repository !");
		}
		return user;
	}

	public User updateUserById(User user, Long Id) throws UserNotFoundException {
		Optional<User> userDB = userRepository.findById(Id);
		if (userDB.isPresent()) {
			user.setId(Id);
			return userRepository.save(user);
		} else {
			throw new UserNotFoundException("User not found in user repository, provide the correct user !");
		}
	}

	public void deleteUserById(Long id) {
		if (!userRepository.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user repository");
		}
		userRepository.deleteById(id);
	}

	public User getUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
