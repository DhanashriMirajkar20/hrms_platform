package com.example.EmployeeManagement.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String companyEmail;
    private String designation;
    private String status;
    private String currentBand;
    private String department;
    private String managerName;
}


