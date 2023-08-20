package com.example.TaskManagementService.service.serviceImp;

import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;
import com.example.TaskManagementService.domain.mapper.TaskMapper;
import com.example.TaskManagementService.error_handling.EntityNotFoundException;
import com.example.TaskManagementService.repository.TaskRepository;
import com.example.TaskManagementService.service.IService.ITaskService;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

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
    public List<TaskDto> getAllTasks() {
      List<Task> tasks = this.taskRepository.findAll();
      List<TaskDto> tasksDto = new ArrayList<>();
      tasks.forEach(task -> {
          tasksDto.add(this.taskMapper.toDto(task));
      });
      return tasksDto;
    }

    @Override
    public Set<TaskDto> getAllUndeletedTasks() {
        Set<Task> tasks = this.taskRepository.getAllUndeletedTasks();
        Set<TaskDto> tasksDto = new HashSet<>();
        tasks.forEach(task -> {
            tasksDto.add(this.taskMapper.toDto(task));
        });
        return tasksDto;
    }

    @Override
    public TaskDto getTask(String taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if(task.isPresent()){
            return this.taskMapper.toDto(task.get());
        }
        else{
            throw new EntityNotFoundException("Task Not Found");
        }
    }

    @Override
    public TaskDto deleteTask(String taskId) {
       Optional<Task>  task = this.taskRepository.findById(taskId);
       if(task.isPresent()){
           task.get().setDeleted(true);
           task.get().setDeleted_at(Timestamp.valueOf(LocalDateTime.now()));
           this.taskRepository.save(task.get());
           return this.taskMapper.toDto(task.get());
       }else{
           throw new EntityNotFoundException("Task Not Found");
       }
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto, String taskId) {
      Optional<Task>  taskToUpdate = this.taskRepository.findById(taskId);
      if(taskToUpdate.isPresent()){
          taskToUpdate.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
          this.taskRepository.save(this.taskMapper.toExistingEntity(taskDto,taskToUpdate.get()));
          return this.taskMapper.toDto(taskToUpdate.get());
      }else {
          throw new EntityNotFoundException("Task Not Found");
      }
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {

              Task task =this.taskMapper.toEntity(taskDto);
        this.taskRepository.save(task);
                return taskDto;
    }

    @Override
    public TaskDto duplicateTask(String taskId) {
       Optional<Task>  originalTask = this.taskRepository.findById(taskId);
       if(originalTask.isPresent()){
        Task copiedTask = SerializationUtils.clone(originalTask.get());
        copiedTask.setId(UUID.randomUUID().toString());
        copiedTask.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        return this.taskMapper.toDto(this.taskRepository.save(copiedTask));}
       else {
           throw  new EntityNotFoundException("Task Not Found");
       }
    }

    @Override
    public TaskDto updateTaskStatus(TaskStatus status,
                                    String TaskId)
    {
      Optional<Task>   taskToUpdate = this.taskRepository.findById(TaskId);
      if (taskToUpdate.isPresent())
      {
        taskToUpdate.get().setTaskStatus(status);
        taskToUpdate.get().setModified_at(Timestamp.valueOf(LocalDateTime.now()));
        return this.taskMapper.toDto(
                this.taskRepository.save(taskToUpdate.get())
        );
      }
      else
      {
          throw new EntityNotFoundException("Task Not Found");
      }
    }
}
