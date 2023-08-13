package com.example.TaskManagementService.domain.dto;
import com.example.TaskManagementService.domain.enums.TaskType;


public class TaskDto {
    private String id;
    private TaskType taskType;
    private String taskDescription;
    private Float taskTime;
    private String taskTitle;
    private Integer taskEstimation;

    public TaskDto() {
    }

    public TaskDto(String id, TaskType taskType, String taskDescription, Float taskTime, String taskTitle, Integer taskEstimation) {
        this.id = id;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.taskTitle = taskTitle;
        this.taskEstimation = taskEstimation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Float getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Float taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskEstimation() {
        return taskEstimation;
    }

    public void setTaskEstimation(Integer taskEstimation) {
        this.taskEstimation = taskEstimation;
    }
}
