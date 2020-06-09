package com.security.todoDemo.services;

import java.util.Optional;

import com.security.todoDemo.entities.User;

public interface IUserService {

	public Optional<User> findByName(String name);
}
