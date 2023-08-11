package com.example.TaskManagementService.domain.mapper;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    TaskDto taskEntityToDto(Task task);
    Task taskDtoToEntity(TaskDto taskDto);
}
