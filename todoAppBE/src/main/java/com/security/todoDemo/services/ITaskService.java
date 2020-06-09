package com.security.todoDemo.services;

import java.util.List;

import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.entities.Task;

public interface ITaskService {
	public List<TaskDto>findAll();
	public List<TaskDto>findByUserName(String userName);
	public List<TaskDto>findByIsDeletedFalse();
	public TaskDto createTask(TaskDto taskDto);
	public TaskDto updateTask(TaskDto taskDto);
	public TaskDto deleteTask(Long id);
}
