package com.example.TaskManagementService.domain.mapper;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import org.springframework.stereotype.Component;

@Component
public class SprintMapper {
    public SprintDto toDto(Sprint sprint){
        return new SprintDto(sprint.getId(), sprint.getSprintTitle(),sprint.getSprintDescription(),sprint.getStartDate(), sprint.getEndDate());
    }
    public Sprint toEntity(SprintDto sprintDto){
        return new Sprint(sprintDto.getSprintTitle(),sprintDto.getSprintDescription(),sprintDto.getStartDate(),sprintDto.getEndDate());
    }
    public Sprint toExistingEntity(SprintDto sprintDto,Sprint sprint){
        if(sprintDto.getSprintTitle() != null){
            sprint.setSprintTitle(sprintDto.getSprintTitle());
        }
        if(sprintDto.getSprintDescription() != null){
            sprint.setSprintDescription(sprintDto.getSprintDescription());
        }
        if(sprintDto.getStartDate() != null){
            sprint.setStartDate(sprintDto.getStartDate());
        }
        if(sprintDto.getEndDate() != null){
            sprint.setEndDate(sprintDto.getEndDate());
        }
        return sprint;
    }
}
