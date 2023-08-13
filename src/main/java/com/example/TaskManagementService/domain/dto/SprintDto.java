package com.example.TaskManagementService.domain.dto;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Timestamp;


public class SprintDto {
    private String id;
    private String sprintTitle;
    private String sprintDescription;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Timestamp startDate;
    private Float sprintDuration;

    public SprintDto() {
    }

    public SprintDto(String id, String sprintTitle, String sprintDescription, Timestamp startDate, Float sprintDuration) {
        this.id = id;
        this.sprintTitle = sprintTitle;
        this.sprintDescription = sprintDescription;
        this.startDate = startDate;
        this.sprintDuration = sprintDuration;
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
}
