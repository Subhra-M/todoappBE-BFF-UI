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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private TaskRepository taskRepository;
    
    @Mock
    private TaskEntityToDtoMapper mapper;

    @InjectMocks
    private TaskService taskService;


    @Test
    void saveTaskUserNotFoundExceptionTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setUser_name("cerb_usr_001");
        when(userRepository.findByName(taskDto.getUser_name())).thenReturn(Optional.empty());
        try {
            taskService.createTask(taskDto);
        }catch (UserNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("User with name " + taskDto.getUser_name() +" not found.");
        }
    }

    @Test
    void saveTaskSuccessfullyTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description");
        taskDto.setDeleted(false);

        User user = new User();
        user.setId(0L);
        user.setCreatedDate(new Date());
        user.setName("cerb_usr_001");

        Task result = new Task(taskDto);

        when(userRepository.findByName(taskDto.getUser_name())).thenReturn(Optional.of(user));
        when(taskRepository.save(any(Task.class))).thenReturn(result);

        TaskDto savedUser = taskService.createTask(taskDto);
        assertThat(savedUser).isNotNull();

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void updateTaskUserNameNotFoundInRequestExceptionTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        try {
            taskService.updateTask(taskDto);
        }catch (UserNameNotPresentInRequestException e){
            assertThat(e.getMessage()).isEqualTo("User name not present in request body.");
        }
    }

    @Test
    void updateTaskTaskIdNotPresentExceptionTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setUser_name("cerb_usr_001");
        try {
            taskService.updateTask(taskDto);
        }catch (TaskIdNotPresentException e){
            assertThat(e.getMessage()).isEqualTo("Task id is not present in the request.");
        }
    }

    @Test
    void updateTaskUserNotFoundExceptionTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description");
        taskDto.setDeleted(false);

        when(userRepository.findByName(taskDto.getUser_name())).thenReturn(Optional.empty());
        Task result = new Task(taskDto);
        when(taskRepository.findById(taskDto.getId())).thenReturn(Optional.of(result));
        try {
            taskService.updateTask(taskDto);
        }catch (UserNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("User with name " + taskDto.getUser_name() +" not found.");
        }
    }

    @Test
    void updateTaskTaskNotFoundExceptionTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setUser_name("cerb_usr_001");
        when(taskRepository.findById(taskDto.getId())).thenReturn(Optional.empty());
        try {
            taskService.updateTask(taskDto);
        }catch (TaskNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Task with Id "+taskDto.getId()+" not found.");
        }
    }

    @Test
    void updateTaskSuccessfullyTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description");
        taskDto.setDeleted(false);

        User user = new User();
        user.setId(0L);
        user.setCreatedDate(new Date());
        user.setName("cerb_usr_001");

        Task result = new Task(taskDto);

        when(userRepository.findByName(taskDto.getUser_name())).thenReturn(Optional.of(user));
        when(taskRepository.findById(taskDto.getId())).thenReturn(Optional.of(result));

        when(taskRepository.save(any(Task.class))).thenReturn(result);

        TaskDto updatedTask = taskService.updateTask(taskDto);
        assertThat(updatedTask).isNotNull();

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void deleteTaskTaskNotFoundExceptionTest(){
        when(taskRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            taskService.deleteTask(0L);
        }catch (TaskNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Task with Id "+0+" not found.");
        }
    }

    @Test
    void deleteTaskSuccessfullyTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setUserId(0L);
        taskDto.setTask_description("Task description");
        taskDto.setDeleted(false);

        Task result = new Task(taskDto);

        User user = new User();
        user.setId(0L);
        user.setCreatedDate(new Date());
        user.setName("cerb_usr_001");

        when(taskRepository.findById(0L)).thenReturn(Optional.of(result));
        when(taskRepository.save(any(Task.class))).thenReturn(result);
        when(userRepository.getOne(result.getUserId())).thenReturn(user);
        TaskDto response = taskService.deleteTask(result.getId());
        assertThat(response).isNotNull();
        assertThat(response.getDeleted()).isEqualTo(true);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void findAllTaskNotFoundExceptionTest(){
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        try {
            taskService.findAll();
        }catch (TaskNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("Task with Id "+0+" not found.");
        }
    }

    @Test
    void findAllSuccessfullyTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0L);
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setUserId(0L);
        taskDto.setTask_description("Task description");
        taskDto.setDeleted(false);

        Task task = new Task(taskDto);
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(taskRepository.findAll()).thenReturn(tasks);
        when(mapper.taskToTaskDTO(task)).thenReturn(taskDto);
        List<TaskDto> response = taskService.findAll();
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(0);
        assertThat(response.get(0).getUser_name()).isEqualTo(taskDto.getUser_name());
    }

    @Test
    void findByUserNameUserNotFoundExceptionTest(){
        String testString = anyString();
        when(userRepository.findByName(testString)).thenReturn(Optional.empty());
    
        try {
            taskService.findByUserName(testString);
        }catch (UserNotFoundException e){
            assertThat(e.getMessage()).isEqualTo("User with name " + testString +" not found.");
        }
    }

    @Test
    void findByUserNameSuccessfullyTest(){
    	String name="cerb_usr_001";
    	 TaskDto taskDto = new TaskDto();
         taskDto.setId(0L);
         taskDto.setModifiedDate(new Date());
         taskDto.setCreatedDate(new Date());
         taskDto.setUser_name("cerb_usr_001");
         taskDto.setTask_description("Task description");
         taskDto.setDeleted(false);

         User user = new User();
         user.setId(0L);
         user.setCreatedDate(new Date());
         user.setName("cerb_usr_001");

         Task task = new Task(taskDto);
         List<Task> tasks = new ArrayList<>();
         tasks.add(task);

         when(userRepository.findByName(name)).thenReturn(Optional.of(user));
         when(taskRepository.findByUserId(user.getId())).thenReturn(tasks);
         when(mapper.taskToTaskDTO(task)).thenReturn(taskDto);
         List<TaskDto> response = taskService.findByUserName(name);
         assertThat(response).isNotNull();
         assertThat(response.size()).isGreaterThan(0);
         assertThat(response.get(0).getUser_name()).isEqualTo(taskDto.getUser_name());

    }
}
