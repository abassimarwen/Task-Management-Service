package com.example.TaskManagementService.domain.dto;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Timestamp;


public class SprintDto {
    private String id;
    private String sprintTitle;
    private String sprintDescription;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Timestamp endDate;

    public SprintDto() {
    }

    public SprintDto(String id, String sprintTitle, String sprintDescription, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.sprintTitle = sprintTitle;
        this.sprintDescription = sprintDescription;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
