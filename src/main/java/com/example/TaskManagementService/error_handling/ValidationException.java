package com.example.TaskManagementService.error_handling;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }

}
