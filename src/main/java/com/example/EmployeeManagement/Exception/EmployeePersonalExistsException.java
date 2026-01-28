package com.example.EmployeeManagement.Exception;

public class EmployeePersonalExistsException extends RuntimeException {
    public EmployeePersonalExistsException(Long id) {
        super("Employee personal already exists for employee id: "+id);
    }
}
