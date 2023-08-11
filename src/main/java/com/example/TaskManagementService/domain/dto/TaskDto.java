package com.example.TaskManagementService.domain.dto;
import com.example.TaskManagementService.domain.enums.TaskType;


public class TaskDto {
    private TaskType taskType;
    private String taskDescription;
    private Float taskTime;
    private String taskTitle;
    private Integer taskEstimation;

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
