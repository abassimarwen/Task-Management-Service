package com.example.TaskManagementService.domain.enums;

public enum SprintStatus {
    IN_PROGRESS("sprint in progress"),
    WAITING("sprint waiting");
    private String sprintStatusDescription;

    SprintStatus(String sprintStatusDescription) {
        this.sprintStatusDescription = sprintStatusDescription;
    }

    public String getSprintStatusDescription() {
        return sprintStatusDescription;
    }
}
