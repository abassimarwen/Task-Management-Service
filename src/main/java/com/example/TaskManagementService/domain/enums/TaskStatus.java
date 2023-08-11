package com.example.TaskManagementService.domain.enums;

public enum TaskStatus {
    TODO("to do"),
    IN_PROGRESS("in progress"),
    UNDER_REVIEW("under review"),
    TEST("test"),
    DONE("done");
    private String taskStatus;

    TaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
}
