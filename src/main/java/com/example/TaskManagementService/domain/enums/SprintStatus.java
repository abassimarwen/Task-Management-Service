package com.example.TaskManagementService.domain.enums;

public enum SprintStatus {
    IN_Progress("sprint in progress"),
    waiting("sprint waiting");
    private String sprintStatusDescription;

    SprintStatus(String sprintStatusDescription) {
        this.sprintStatusDescription = sprintStatusDescription;
    }

    public String getSprintStatusDescription() {
        return sprintStatusDescription;
    }
}
