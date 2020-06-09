package com.security.todoDemo.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.todoDemo.entities.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	public List<Task> findByUserId(Long userId);
	public List<Task> findByIsDeletedFalse();
}
