package com.example.TaskManagementService.domain.mapper;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SprintMapper {
    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);
    SprintDto sprintEntityToDto(Sprint sprint);
    Sprint sprintDtoToEntity(SprintDto sprintDto);
}
