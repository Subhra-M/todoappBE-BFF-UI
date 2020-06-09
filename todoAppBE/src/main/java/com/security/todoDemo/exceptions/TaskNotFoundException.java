package com.security.todoDemo.exceptions;

public class TaskNotFoundException extends RuntimeException{
    public TaskNotFoundException(Long id){
        super(String.format("Task with Id %d not found.", id));
    }
}
