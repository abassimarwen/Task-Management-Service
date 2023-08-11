package com.example.TaskManagementService.domain.enums;

public enum TaskType {
    FEATURE("new feature"),
    BUGFIX("bugfix"),
    DOCUMENTATION("documentation");
    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
