package com.example.TaskManagementService.domain.entity;

import com.example.TaskManagementService.domain.enums.SprintStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Sprint implements Serializable {
    @Id
    private String id;
    private String sprintTitle;
    private String sprintDescription;
    @Enumerated(EnumType.STRING)
    private SprintStatus sprintStatus;
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
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startDate;

    private Float sprintDuration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="sprint",fetch = FetchType.EAGER)
    private Set<Task> tasks;

    public Sprint() {
        this.id =  UUID.randomUUID().toString();
        this.created_at = Timestamp.valueOf(LocalDateTime.now());
        this.isDeleted = false;
        this.sprintStatus = SprintStatus.waiting;
    }

    public Sprint(String id, String sprintTitle, String sprintDescription, SprintStatus sprintStatus, Timestamp created_at, Timestamp modified_at, Timestamp deleted_at, Boolean isDeleted, Timestamp startDate, Float sprintDuration, Timestamp endDate, Set<Task> tasks) {
        this.id = id;
        this.sprintTitle = sprintTitle;
        this.sprintDescription = sprintDescription;
        this.sprintStatus = sprintStatus;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.deleted_at = deleted_at;
        this.isDeleted = isDeleted;
        this.startDate = startDate;
        this.sprintDuration = sprintDuration;
        this.tasks = tasks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSprintTitle() {
        return sprintTitle;
    }

    public void setSprintTitle(String sprintTitle) {
        this.sprintTitle = sprintTitle;
    }

    public String getSprintDescription() {
        return sprintDescription;
    }

    public void setSprintDescription(String sprintDescription) {
        this.sprintDescription = sprintDescription;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
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

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Float getSprintDuration() {
        return sprintDuration;
    }

    public void setSprintDuration(Float sprintDuration) {
        this.sprintDuration = sprintDuration;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
