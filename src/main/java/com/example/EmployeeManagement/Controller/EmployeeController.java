package com.example.EmployeeManagement.Controller;

import com.example.EmployeeManagement.DTO.EmployeeDTO;
import com.example.EmployeeManagement.Service.EmployeeService;
import com.example.EmployeeManagement.Model.Employee;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hrms")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/")
    public String message(){
        return "Hello";
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        List<EmployeeDTO> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }

    // GET /api/v1/hrms/employees/{id}
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeCoreById(@PathVariable("id") Long id){
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

    // GET /api/v1/hrms/employees/{id}/profile
    @GetMapping("/employees/{id}/profile")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id){
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }

//    @PostMapping("/employees")
//    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody Employee employee){
//        EmployeeDTO savedEmployee = employeeService.addEmployee(employee);
//        return ResponseEntity.ok(savedEmployee);
//    }
@PostMapping(
        value = "/employees",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<?> createEmployee(
        @RequestPart("employee") String employeeJson,
        @RequestPart("photo") MultipartFile photo) {

    try {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registeredModules();

        Employee employee = mapper.readValue(employeeJson, Employee.class);
        employee.setPhoto(photo.getBytes());

        return ResponseEntity.ok(employeeService.addEmployee(employee));

    } catch (Exception e) {
        e.printStackTrace(); // NOW YOU WILL SEE ERROR IF ANY
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}



    // PUT /api/v1/hrms/employees/{id}
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id,
                                                      @RequestBody Employee employee){
        EmployeeDTO updated = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
