package com.example.TaskManagementService.service.IService;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;

import java.util.List;
import java.util.Set;

public interface ITaskService {
    List<TaskDto> getAllTasks();
    Set<TaskDto> getAllUndeletedTasks();
    TaskDto getTask(String taskId);
    TaskDto deleteTask(String taskId);
    TaskDto updateTask(TaskDto taskDto,String taskId);
    TaskDto createTask(TaskDto taskDto);
    TaskDto duplicateTask(String taskId);
    TaskDto updateTaskStatus(TaskStatus status , String TaskId);
    Set<TaskDto> getSprintTasks(String SprintId);
    TaskDto affectTaskToSprint(String taskId,String sprintId);
}
