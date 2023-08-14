package com.example.TaskManagementService.service.IService;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.enums.SprintStatus;

import java.util.Set;

public interface ISprintService {
    public Sprint createSprint(SprintDto sprintDto);
    public void fillSprint(TaskDto taskDto, String sprintId);
    public void deleteSprint(String sprintId);
    public void updateSprint(String sprintId,SprintDto sprintDto);
    public SprintDto getSprint(String sprintId);
    public Set<SprintDto> getSprints();
    public Set<SprintDto> getUndeletedSprints();
    public void updateSprintStatus(SprintStatus sprintStatus,String sprintId);
}
