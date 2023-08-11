package com.example.TaskManagementService.service.serviceImp;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;
import com.example.TaskManagementService.domain.mapper.TaskMapper;
import com.example.TaskManagementService.repository.TaskRepository;
import com.example.TaskManagementService.service.IService.ITaskService;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class TaskServiceImp implements ITaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Autowired
    public TaskServiceImp(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @Override
    public Set<Task> getAllUndeletedTasks() {
        return this.taskRepository.getAllUndeletedTasks();
    }

    @Override
    public Task getTask(String taskId) {
        return this.taskRepository.findById(taskId).orElseThrow();
    }

    @Override
    public void deleteTask(String taskId) {
         this.taskRepository.deleteById(taskId);
    }

    @Override
    public Task updateTask(TaskDto taskDto, String taskId) {
        Task taskToUpdate = this.taskRepository.findById(taskId).orElseThrow();
        Task task = this.taskMapper.taskDtoToEntity(taskDto);
        return null;
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        Task task = this.taskMapper.taskDtoToEntity(taskDto);
        return this.taskRepository.save(task);
    }

    @Override
    public Task duplicateTask(String taskId) {
        Task originalTask = this.taskRepository.findById(taskId).orElseThrow();
        Task copiedTask = SerializationUtils.clone(originalTask);
        return this.taskRepository.save(copiedTask);
    }

    @Override
    public Task updateTaskStatus(TaskStatus status, String TaskId) {
        Task task = this.taskRepository.findById(TaskId).orElseThrow();
        task.setTaskStatus(status);
        return this.taskRepository.save(task);
    }
}
