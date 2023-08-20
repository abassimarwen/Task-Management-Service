package com.example.TaskManagementService.error_handling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> entityNotFound(EntityNotFoundException e){
      ApiException apiException=  new ApiException(
        e.getMessage(),
        HttpStatus.NOT_FOUND,
        Timestamp.valueOf(LocalDateTime.now())
);
      log.error("entity not found");
      return new ResponseEntity<>(apiException,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> notValid(ValidationException e){
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE,
                Timestamp.valueOf(LocalDateTime.now())


                );
        return new ResponseEntity<>(apiException,HttpStatus.NOT_ACCEPTABLE);
    }
}
