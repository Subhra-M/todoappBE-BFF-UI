package com.security.todoDemo.services;

import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.entities.Task;
import com.security.todoDemo.entities.User;
import com.security.todoDemo.exceptions.TaskIdNotPresentException;
import com.security.todoDemo.exceptions.TaskNotFoundException;
import com.security.todoDemo.exceptions.UserNameNotPresentInRequestException;
import com.security.todoDemo.exceptions.UserNotFoundException;
import com.security.todoDemo.mapper.TaskEntityToDtoMapper;
import com.security.todoDemo.repositiories.TaskRepository;
import com.security.todoDemo.repositiories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService{
	@Autowired
	UserRepository userRepository;

	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskEntityToDtoMapper mapper ;
	
	@Override
	public List<TaskDto> findAll() {
		 List<Task> tasks=taskRepository.findAll();
		 if(tasks.isEmpty())
			 throw new TaskNotFoundException(0L);
		return  tasks.stream().map(entity-> 
		mapper.taskToTaskDTO(entity))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<TaskDto> findByIsDeletedFalse() {
		 List<Task> tasks=taskRepository.findByIsDeletedFalse();
		 if(tasks.isEmpty())
			 throw new TaskNotFoundException(0L);
		return  tasks.stream().map(entity-> 
		mapper.taskToTaskDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public List<TaskDto> findByUserName(String userName) {
		List<Task> tasks;
		Optional<User> user=userRepository.findByName(userName);
		if(user.isPresent()){
			tasks=taskRepository.findByUserId(user.get().getId());
			return tasks.stream().map(entity-> mapper.taskToTaskDTO(entity))
					.collect(Collectors.toList());
		}else {
			throw new UserNotFoundException(userName);
		}
	
	}
	public TaskDto createTask(TaskDto taskDto) {
		Optional<User> user = userRepository.findByName(taskDto.getUser_name());
		if(user.isPresent()) {
			taskDto.setUserId(user.get().getId());
			taskDto.setCreatedDate(new Date());
			taskDto.setModifiedDate(new Date());
			taskDto.setDeleted(false);
			Task task = taskRepository.save(new Task(taskDto));
			taskDto.setId(task.getId());
		}
		else {
			throw new UserNotFoundException(taskDto.getUser_name());
		}
		return taskDto;
	}

	public TaskDto updateTask(TaskDto taskDto) {
		if (taskDto.getUser_name() == null)
			throw new UserNameNotPresentInRequestException();
		if (taskDto.getId() == null)
			throw new TaskIdNotPresentException();
		Optional<Task> optionalTask = taskRepository.findById(taskDto.getId());
		if (!optionalTask.isPresent())
			throw new TaskNotFoundException(taskDto.getId());
		Optional<User> user = userRepository.findByName(taskDto.getUser_name());
		if(user.isPresent()) {
			Task task = optionalTask.get();
			task.setModifiedDate(new Date());
			taskDto.setModifiedDate(new Date());
			task.setDescription(taskDto.getTask_description());
			taskRepository.save(task);
		}else {
			throw new UserNotFoundException(taskDto.getUser_name());
		}
		return taskDto;
	}

	@Override
	public TaskDto deleteTask(Long id) {
		TaskDto taskDto;
		Optional<Task> optionalTask = taskRepository.findById(id);
		if (!optionalTask.isPresent())
			throw new TaskNotFoundException(id);
		else{
			Task task = optionalTask.get();
			task.setDeleted(true);
			taskRepository.save(task);
			taskDto = new TaskDto(task, userRepository.getOne(task.getUserId()).getName());
		}
		return taskDto;
	}


}
