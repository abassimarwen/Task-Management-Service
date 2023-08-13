package com.example.TaskManagementService.domain.enums;

public enum TaskStatus {
    TODO("to do"),
    IN_PROGRESS("in progress"),
    UNDER_REVIEW("under review"),
    TEST("test"),
    DONE("done"),
    WAITING("waiting to be assigned");
    private String taskStatusDescription;

    TaskStatus(String taskStatusDescription) {
        this.taskStatusDescription = taskStatusDescription;
    }

    public String getTaskStatusDescription() {
        return taskStatusDescription;
    }
}
