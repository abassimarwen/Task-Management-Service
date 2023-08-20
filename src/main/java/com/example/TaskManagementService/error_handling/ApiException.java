package com.example.TaskManagementService.error_handling;


import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public class ApiException {
    private String message;
    private HttpStatus  httpStatus;
    private Timestamp timestamp;

    public ApiException() {
    }



    public ApiException(String message,
                        HttpStatus httpStatus,
                        Timestamp timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }


}
