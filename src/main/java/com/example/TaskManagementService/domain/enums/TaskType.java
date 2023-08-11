package com.example.TaskManagementService.domain.enums;

public enum TaskType {
    FEATURE("new feature"),
    BUGFIX("bugfix"),
    DOCUMENTATION("documentation");
    private String typeStatusDescription;

    TaskType(String typeStatusDescription) {
        this.typeStatusDescription = typeStatusDescription;
    }
    public String getTypeStatusDescription() {
        return typeStatusDescription;
    }
}
