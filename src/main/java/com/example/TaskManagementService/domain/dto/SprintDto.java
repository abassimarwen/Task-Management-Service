package com.example.TaskManagementService.domain.dto;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.sql.Timestamp;


public class SprintDto {
    private String sprintTitle;
    private String sprintDescription;
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private Timestamp startDate;
    private Float sprintDuration;
}
