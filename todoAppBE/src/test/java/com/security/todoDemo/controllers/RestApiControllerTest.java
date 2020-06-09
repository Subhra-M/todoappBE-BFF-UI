package com.security.todoDemo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.services.ITaskService;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers=RestApiController.class)
public class RestApiControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ITaskService taskService;
	private List<TaskDto> taskList;
	TaskDto t1=new TaskDto();

	@BeforeEach
	void setUp() {
		this.taskList=new ArrayList<TaskDto>();
		t1.setId(1L);
		t1.setUserId(11L);
		t1.setDeleted(false);
		t1.setCreatedDate(new Date());
		t1.setModifiedDate(new Date());
		t1.setTask_description("test1");
		t1.setUser_name("cerb_usr_001");
		TaskDto t2=new TaskDto();
		t2.setId(2L);
		t2.setUserId(11L);
		t2.setDeleted(false);
		t2.setCreatedDate(new Date());
		t2.setModifiedDate(new Date());
		t2.setTask_description("test2");

		this.taskList.add(t1);
		this.taskList.add(t2);
	}
	@Test
	void shouldFetchAllUser() throws Exception{
		given(taskService.findAll()).willReturn(taskList);

		this.mockMvc.perform(get("/todoapp/api/v1/task"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(taskList.size())));

	}
	@Test
	void shouldFetchByUserName() throws Exception {


		given(taskService.findByUserName("user1")).willReturn(taskList);

		this.mockMvc.perform(get("/todoapp/api/v1/task/{userName}", "user1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()", is(taskList.size())));
	}

	@Test
	void createTaskTest() throws Exception {
		ObjectMapper objectMapper=new ObjectMapper();
		when(taskService.createTask(any(TaskDto.class))).thenReturn(t1);
		mockMvc.perform(post("/todoapp/api/v1/task")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(t1)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.userId").value(11));
	}

	@Test
	void updateTaskTest() throws Exception {
		t1.setTask_description("updated task description");
		ObjectMapper objectMapper=new ObjectMapper();
		when(taskService.updateTask(any(TaskDto.class))).thenReturn(t1);
		mockMvc.perform(put("/todoapp/api/v1/task")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(t1)))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.task_description").value("updated task description"));
	}

	@Test
	void deleteTest() throws Exception{
		t1.setDeleted(true);
		when(taskService.deleteTask(anyLong())).thenReturn(t1);
		mockMvc.perform(delete("/todoapp/api/v1/task/"+t1.getId())
				.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.deleted").value(true));
	}
}