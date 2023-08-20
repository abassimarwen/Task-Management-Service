package com.example.TaskManagementService.web;


import com.example.TaskManagementService.domain.dto.TaskDto;
import com.example.TaskManagementService.domain.entity.Task;
import com.example.TaskManagementService.domain.enums.TaskStatus;
import com.example.TaskManagementService.service.IService.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
         return new ResponseEntity<>(this.taskService.createTask(taskDto),HttpStatus.CREATED);
    }
    @PostMapping("/duplicate/{taskId}")
    public ResponseEntity<TaskDto> createTask(@PathVariable("taskId") String taskId){
        return new ResponseEntity<>(this.taskService.duplicateTask(taskId),HttpStatus.OK);
    }
    @PutMapping("/update-status/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable("taskId") String taskId,
                                                    @RequestParam TaskStatus taskStatus){
        return  new ResponseEntity<>(this.taskService.updateTaskStatus(taskStatus,taskId),HttpStatus.OK);
    }
    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("taskId")String taskId,@RequestBody TaskDto taskDto){
      return new ResponseEntity<>(this.taskService.updateTask(taskDto,taskId),HttpStatus.OK)  ;
    }
    @PutMapping("/delete/{taskId}")
    public  ResponseEntity<TaskDto> deleteTask(@PathVariable("taskId")String taskId){
       return new ResponseEntity<>(this.taskService.deleteTask(taskId),HttpStatus.OK) ;
    }
    @GetMapping()
    public ResponseEntity<List<TaskDto>> getAllTasks(){

        return new ResponseEntity<>(this.taskService.getAllTasks(),HttpStatus.OK);
    }
    @GetMapping("/undeleted")
    public ResponseEntity<Set<TaskDto>> getAllUndeletedTasks(){

        return new ResponseEntity<>(this.taskService.getAllUndeletedTasks(),HttpStatus.OK);
    }
    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("taskId")String taskId){
        return new ResponseEntity<>(this.taskService.getTask(taskId), HttpStatus.OK);
    }
    @GetMapping("/tasks-per-sprints/{sprintId}")
    public ResponseEntity<Set<TaskDto>> getTasksPerSprint(@PathVariable("sprintId")String sprintId){
        return new ResponseEntity<>(this.taskService.getSprintTasks(sprintId),HttpStatus.OK);
    }
    @PutMapping("/affect-task/{taskId}/{sprintId}")
    public ResponseEntity<TaskDto> affectTaskToSprint(@PathVariable("taskId") String taskId,
                                                      @PathVariable("sprintId")String sprintId){
        return  new ResponseEntity<>(this.taskService.affectTaskToSprint(taskId,sprintId),HttpStatus.OK);
    }
}
