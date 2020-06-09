package com.security.todoDemo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.security.todoDemo.entities.Task;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskDto {
    private Long id;
    private Long userId;
    private String user_name;
    private String task_description;
    private Date createdDate;
    private Date modifiedDate;
    private Boolean isDeleted;

    public TaskDto(){}

    public TaskDto(Task task, String user_name){
        this.id = task.getId();
        this.userId = task.getUserId();
        this.task_description = task.getDescription();
        this.isDeleted = task.getDeleted();
        this.createdDate = task.getCreatedDate();
        this.modifiedDate = task.getModifiedDate();
        this.user_name = user_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
