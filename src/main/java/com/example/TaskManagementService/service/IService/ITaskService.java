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
    void deleteTask(String taskId);
    void updateTask(TaskDto taskDto,String taskId);
    Task createTask(TaskDto taskDto);
    Task duplicateTask(String taskId);
    Task updateTaskStatus(TaskStatus status , String TaskId);
}
