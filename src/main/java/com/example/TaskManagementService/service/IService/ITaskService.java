package com.example.TaskManagementService.service.IService;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;

import java.util.List;
import java.util.Set;

public interface ITaskService {
    List<Task> getAllTasks();
    Set<Task> getAllUndeletedTasks();
    Task getTask(String taskId);
    void deleteTask(String taskId);
    Task updateTask(TaskDto taskDto,String taskId);
    Task createTask(TaskDto taskDto);
    Task duplicateTask(String taskId);
    Task updateTaskStatus(TaskStatus status , String TaskId);
}
