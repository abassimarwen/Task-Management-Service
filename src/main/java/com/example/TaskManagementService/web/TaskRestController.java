package com.example.TaskManagementService.web;


import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;
import com.example.TaskManagementService.service.IService.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final ITaskService taskService;
    @Autowired
    public TaskRestController(ITaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping
    public Task createTask(@RequestBody TaskDto taskDto){
         return this.taskService.createTask(taskDto);
    }
    @PostMapping("/duplicate/{taskId}")
    public Task createTask(@PathVariable("taskId") String taskId){
        return this.taskService.duplicateTask(taskId);
    }
    @PutMapping("/update-status/{taskId}")
    public Task updateTaskStatus(@PathVariable("taskId") String taskId, @RequestParam TaskStatus taskStatus){
        return  this.taskService.updateTaskStatus(taskStatus,taskId);
    }
    @PutMapping("/{taskId}")
    public void updateTask(@PathVariable("taskId")String taskId,@RequestBody TaskDto taskDto){
        this.taskService.updateTask(taskDto,taskId);
    }
    @PutMapping("/delete/{taskId}")
    public  void deleteTask(@PathVariable("taskId")String taskId){
        this.taskService.deleteTask(taskId);
    }
    @GetMapping()
    public List<Task> getAllTasks(){
       return this.taskService.getAllTasks();
    }
    @GetMapping("/undeleted")
    public Set<Task> getAllUndeletedTasks(){
        return this.taskService.getAllUndeletedTasks();
    }
    @GetMapping("/{taskId}")
    public Task getTask(@PathVariable("taskId")String taskId){
        return this.taskService.getTask(taskId);
    }
}
