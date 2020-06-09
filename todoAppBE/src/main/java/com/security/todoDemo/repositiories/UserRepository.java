package com.security.todoDemo.repositiories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.todoDemo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	Optional<User> findByName(String name);

}
