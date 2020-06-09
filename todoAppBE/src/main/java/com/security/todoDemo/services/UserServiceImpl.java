package com.security.todoDemo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.todoDemo.entities.User;
import com.security.todoDemo.repositiories.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
private UserRepository userRepository;
	@Override
	public Optional<User> findByName(String Name) {
		// TODO Auto-generated method stub
		return userRepository.findByName(Name);
	}

}
