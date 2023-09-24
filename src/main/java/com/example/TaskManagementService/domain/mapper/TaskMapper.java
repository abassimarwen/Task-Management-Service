package com.example.TaskManagementService.domain.mapper;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDto toDto(Task  task){
        return new TaskDto(task.getId(),
                                      task.getTaskType(),
                                      task.getTaskDescription(),
                                      task.getTaskTime(),
                                      task.getTaskTitle(),
                                      task.getTaskEstimation(),
                                      task.getTaskStatus());
    }
    public Task toEntity(TaskDto taskDto){
         return new Task(
                 taskDto.getTaskType(),
                 taskDto.getTaskDescription(),
                 taskDto.getTaskTime(),
                 taskDto.getTaskTitle(),
                 taskDto.getTaskEstimation(),
                 taskDto.getTaskStatus()
         );
    }
    public Task toExistingEntity(TaskDto taskDto,Task task){
        if(taskDto.getTaskEstimation() != null){task.setTaskEstimation(taskDto.getTaskEstimation());}
        if(taskDto.getTaskTitle() != null){task.setTaskTitle(taskDto.getTaskTitle());}
        if(taskDto.getTaskTime() != null){task.setTaskTime(taskDto.getTaskTime());}
        if(taskDto.getTaskDescription() != null){task.setTaskDescription(taskDto.getTaskDescription());}
        if(taskDto.getTaskType() != null){task.setTaskType(taskDto.getTaskType());}
        if(taskDto.getTaskStatus() != null){task.setTaskStatus(taskDto.getTaskStatus());}
        return task;
    }
}
