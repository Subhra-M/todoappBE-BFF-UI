package com.security.todoDemo.exceptions;

public class TaskIdNotPresentException extends RuntimeException {
    public TaskIdNotPresentException(){
        super("Task id is not present in the request.");
    }
}
