package com.security.todoDemo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.security.todoDemo.dtos.TaskDto;
import com.security.todoDemo.entities.Task;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TaskEntityToDtoMapper{
	//TaskEntityToDtoMapper INSTANCE = Mappers.getMapper(TaskEntityToDtoMapper.class);
	@Mappings({
		@Mapping(target="id", source="entity.id"),
		@Mapping(target="userId", source="entity.userId"),
		@Mapping(target="task_description", source="entity.description"),
		@Mapping(target="createdDate", source="entity.createdDate"),
		@Mapping(target="modifiedDate", source="entity.modifiedDate")
		//@Mapping(target="isDeleted", source="entity.isDeleted")
		})

		TaskDto taskToTaskDTO(Task entity);
}
