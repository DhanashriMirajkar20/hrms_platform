package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.DTO.EmployeePersonalDTO;
import com.example.EmployeeManagement.Model.EmployeePersonal;
import com.example.EmployeeManagement.Service.EmployeePersonalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hrms/employees")
@AllArgsConstructor
public class EmployeePersonalController {
    private EmployeePersonalService employeePersonalService;

    @GetMapping("/personal-details")
    public ResponseEntity<List<EmployeePersonalDTO>> getAllEmployeePersonal(){
        List<EmployeePersonalDTO> empDto = employeePersonalService.getAllEmployeesPersonal();
        return ResponseEntity.ok(empDto);
    }


    @GetMapping("/personal-details/{id}")
    public ResponseEntity<EmployeePersonalDTO> getEmployeesPersonalById(@PathVariable("id") Long id){
        EmployeePersonalDTO empDto = employeePersonalService.getEmployeePersonalById(id);
        return ResponseEntity.ok(empDto);
    }


    @GetMapping("/{id}/personal")
    public ResponseEntity<EmployeePersonalDTO> getEmployeesPersonalByEmployeeId(@PathVariable("id") Long id){
        EmployeePersonalDTO empDto = employeePersonalService.getEmployeePersonalByEmployeeId(id);
        return ResponseEntity.ok(empDto);
    }

        return ResponseEntity.ok(empDto);
    }

    @DeleteMapping("/personal-details/{id}")
    public ResponseEntity<String> deleteEmployeePersonalById(@PathVariable("id") Long id){
        employeePersonalService.deleteEmployeePersonalById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

}
