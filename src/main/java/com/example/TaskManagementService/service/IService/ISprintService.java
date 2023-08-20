package com.example.TaskManagementService.service.IService;

import com.example.TaskManagementService.domain.dto.SprintDto;
import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Sprint;
import com.example.TaskManagementService.domain.enums.SprintStatus;

import java.util.Set;

public interface ISprintService {
    public SprintDto createSprint(SprintDto sprintDto);
    public SprintDto fillSprint(String taskId, String sprintId);
    public SprintDto deleteSprint(String sprintId);
    public SprintDto updateSprint(String sprintId,SprintDto sprintDto);
    public SprintDto getSprint(String sprintId);
    public Set<SprintDto> getSprints();
    public Set<SprintDto> getUndeletedSprints();
    public SprintDto updateSprintStatus(SprintStatus sprintStatus,String sprintId);
}
