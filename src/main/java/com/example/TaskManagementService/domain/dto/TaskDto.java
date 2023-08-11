package com.example.TaskManagementService.domain.dto;
import com.example.TaskManagementService.domain.enums.TaskType;


public class TaskDto {
    private TaskType taskType;
    private String taskDescription;
    private Float taskTime;
    private String taskTitle;
    private Integer taskEstimation;
}
