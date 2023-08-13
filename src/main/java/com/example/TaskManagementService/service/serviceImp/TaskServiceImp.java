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


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
       Optional<Task>  task = this.taskRepository.findById(taskId);
       if(task.isPresent()){
           task.get().setDeleted(true);
           this.taskRepository.save(task.get());
       }
    }

    @Override
    public void updateTask(TaskDto taskDto, String taskId) {
      Optional<Task>  taskToUpdate = this.taskRepository.findById(taskId);
      if(taskToUpdate.isPresent()){
          this.taskRepository.save(this.taskMapper.toExistingEntity(taskDto,taskToUpdate.get()));
      }
    }

    @Override
    public Task createTask(TaskDto taskDto) {
        return this.taskRepository.save(this.taskMapper.toEntity(taskDto));
    }

    @Override
    public Task duplicateTask(String taskId) {
        Task originalTask = this.taskRepository.findById(taskId).orElseThrow();
        Task copiedTask = SerializationUtils.clone(originalTask);
        copiedTask.setId(UUID.randomUUID().toString());
        copiedTask.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        return this.taskRepository.save(copiedTask);
    }

    @Override
    public Task updateTaskStatus(TaskStatus status, String TaskId) {
        Task task = this.taskRepository.findById(TaskId).orElseThrow();
        task.setTaskStatus(status);
        return this.taskRepository.save(task);
    }
}
