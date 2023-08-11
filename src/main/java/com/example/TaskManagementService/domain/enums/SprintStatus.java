package com.example.TaskManagementService.domain.enums;

public enum SprintStatus {
    IN_Progress("sprint in progress"),
    waiting("sprint waiting");
    private String sprintStatus;

    SprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public String getSprintStatus() {
        return sprintStatus;
    }
}
