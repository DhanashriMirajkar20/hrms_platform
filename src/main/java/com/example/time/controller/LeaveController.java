package com.example.time.controller;

import com.example.EmployeeManagement.Model.Employee;
import com.example.EmployeeManagement.Repository.EmployeeRepository;
import com.example.time.dto.LeaveRequestDTO;
import com.example.time.entity.LeaveRequest;
import com.example.time.mapper.LeaveRequestMapper;
import com.example.time.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hrms/time/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * POST /api/v1/hrms/time/leaves/apply
     */
    @PostMapping("/apply")
    public LeaveRequestDTO applyLeave(@RequestBody LeaveRequestDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found: " + dto.getEmployeeId()));
        LeaveRequest entity = LeaveRequestMapper.toEntity(dto, employee);
        LeaveRequest saved = leaveService.applyLeave(entity);
        return LeaveRequestMapper.toDTO(saved);
    }

    /**
     * PUT /api/v1/hrms/time/leaves/{leaveRequestId}/approve
     */
    @PutMapping("/{leaveRequestId}/approve")
    public LeaveRequestDTO approveLeave(@PathVariable Long leaveRequestId,
                                        @RequestParam Long approverId) {
        LeaveRequest approved = leaveService.approveLeave(leaveRequestId, approverId);
        return LeaveRequestMapper.toDTO(approved);
    }

    /**
     * PUT /api/v1/hrms/time/leaves/{leaveRequestId}/reject
     */
    @PutMapping("/{leaveRequestId}/reject")
    public LeaveRequestDTO rejectLeave(@PathVariable Long leaveRequestId,
                                       @RequestParam Long approverId) {
        LeaveRequest rejected = leaveService.rejectLeave(leaveRequestId, approverId);
        return LeaveRequestMapper.toDTO(rejected);
    }
}
