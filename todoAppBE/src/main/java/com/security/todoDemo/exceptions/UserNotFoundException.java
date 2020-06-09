package com.security.todoDemo.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id){
        super(String.format("User with Id %d not found.", id));
    }

    public UserNotFoundException(String name){
        super("User with name " + name +" not found.");
    }
}
