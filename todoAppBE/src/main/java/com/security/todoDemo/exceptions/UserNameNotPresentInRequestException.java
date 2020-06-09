package com.security.todoDemo.exceptions;

public class UserNameNotPresentInRequestException extends RuntimeException {
    public UserNameNotPresentInRequestException(){
        super("User name not present in request body.");
    }
}
