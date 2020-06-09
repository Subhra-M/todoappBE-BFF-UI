package com.security.todoDemo.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.services.ITaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Todo App pertaining to TODO in Online application")
@RequestMapping(value = "/api")
public class RestApiController {

	@Autowired
	public ITaskService taskService;

	@ApiOperation(value = "View a list of all Todo entered by all users",response = List.class)
	@ApiResponses(value = {
		   @ApiResponse  (code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 200, message = "Successfully retrieved todo list",
				response = List.class) })
	@GetMapping ("/v1/task")
	public List<TaskDto> getAllTask() {
		//return taskService.findAll();
		return taskService.findByIsDeletedFalse();
	}
	@ApiOperation(value = "View a list of all Todo entered by the given Username",response = List.class)
	@ApiResponses(value = {
		   @ApiResponse  (code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 200, message = "Successfully retrieved todo list for the given user",
				response = List.class) })
	@GetMapping ("/v1/task/{userName}")
	public List<TaskDto> getTasksByUserName(@PathVariable String userName) {
		return taskService.findByUserName(userName);
	}

	@ApiOperation(value = "Create Todo entered by the user in the database",response = TaskDto.class)
	@ApiResponses(value = {
		   @ApiResponse  (code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 200, message = "Successfully entered todo information entered by the user",
				response = TaskDto.class) })
	@PostMapping(value = "/v1/task")
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
		return ResponseEntity.status(CREATED).body(taskService.createTask(taskDto));
	}

	@ApiOperation(value = "Update Todo entered by the user in the database",response = TaskDto.class)
	@ApiResponses(value = {
		   @ApiResponse  (code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 200, message = "Successfully update todo information entered by the user",
				response = TaskDto.class) })
	@PutMapping(value = "/v1/task")
	public TaskDto updateTask(@RequestBody TaskDto taskDto){
		return taskService.updateTask(taskDto);
	}

	@ApiOperation(value = "Delete Todo entered by the user in the database",response = TaskDto.class)
	@ApiResponses(value = {
			@ApiResponse  (code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 200, message = "Successfully deleted todo information entered by the user",
					response = TaskDto.class) })
	@DeleteMapping(value = "/v1/task/{id}")
	public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id){
		return ResponseEntity.ok(taskService.deleteTask(id));
	}
}
