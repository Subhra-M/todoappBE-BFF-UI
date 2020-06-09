package com.security.todoDemo.repositiories;

import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.entities.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void saveTaskTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description test");
        taskDto.setDeleted(false);
        taskDto.setUserId(1L);
        Task task = new Task(taskDto);
        task = entityManager.persistAndFlush(task);
        assertThat(taskRepository.findById(task.getId()).get()).isEqualTo(task);
    }

    @Test
    public void findByUserIdTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description test");
        taskDto.setDeleted(false);
        taskDto.setUserId(1L);
        Task task = new Task(taskDto);
        task = entityManager.persistAndFlush(task);

        assertThat(taskRepository.findByUserId(taskDto.getUserId()).get(0).getUserId()).isEqualTo(task.getUserId());
    }

    @Test
    public void findAllTest(){
        TaskDto taskDto = new TaskDto();
        taskDto.setModifiedDate(new Date());
        taskDto.setCreatedDate(new Date());
        taskDto.setUser_name("cerb_usr_001");
        taskDto.setTask_description("Task description test");
        taskDto.setDeleted(false);
        taskDto.setUserId(1L);
        Task task = new Task(taskDto);
        task = entityManager.persistAndFlush(task);

        assertThat(taskRepository.findAll()).isNotNull();
    }
}
