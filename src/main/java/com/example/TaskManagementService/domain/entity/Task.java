package com.example.TaskManagementService.domain.entity;

import com.example.TaskManagementService.domain.enums.TaskStatus;
import com.example.TaskManagementService.domain.enums.TaskType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_at;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modified_at;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deleted_at;
    private Boolean isDeleted;
    @Enumerated(EnumType.STRING)
    private TaskType taskType;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    private String taskDescription;
    private Float taskTime;
    private String taskTitle;
    private Integer taskEstimation;
    @ManyToOne()
    private Sprint sprint;

    public Task() {
        this.id =  UUID.randomUUID().toString();
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.isDeleted = false;
        this.taskStatus = TaskStatus.WAITING;
    }

    public Task(TaskType taskType, String taskDescription, Float taskTime, String taskTitle, Integer taskEstimation) {
        this.id =  UUID.randomUUID().toString();
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.isDeleted = false;
        this.taskStatus = TaskStatus.WAITING;
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskTime = taskTime;
        this.taskTitle = taskTitle;
        this.taskEstimation = taskEstimation;
    }
    public Task(TaskType taskType, String taskDescription, Float taskTime, String taskTitle, Integer taskEstimation,TaskStatus taskStatus) {
        this.id =  UUID.randomUUID().toString();
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.isDeleted = false;
        this.taskStatus = taskStatus;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public Timestamp getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Timestamp deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }
}
