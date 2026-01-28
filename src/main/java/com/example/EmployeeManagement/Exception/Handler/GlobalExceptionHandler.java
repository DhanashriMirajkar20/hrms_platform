package com.example.EmployeeManagement.Exception.Handler;


import com.example.EmployeeManagement.DTO.ApiDto;
import com.example.EmployeeManagement.Exception.EmployeeNotFoundException;
import com.example.EmployeeManagement.Exception.EmployeePersonalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public ResponseEntity<ApiDto> handleEmployeeNotFound(EmployeeNotFoundException ex){
        ApiDto error = new ApiDto(
          404,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    public ResponseEntity<ApiDto> handleEmployeePersonalNotFound(EmployeePersonalNotFoundException ex){
        ApiDto error = new ApiDto(
                404,
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
