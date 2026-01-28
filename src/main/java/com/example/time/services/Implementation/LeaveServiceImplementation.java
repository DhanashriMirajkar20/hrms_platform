package com.example.time.services.Implementation;

import com.example.time.entity.LeaveBalance;
import com.example.time.entity.LeaveRequest;
import com.example.time.repository.LeaveBalanceRepository;
import com.example.time.repository.LeaveRequestRepository;
import com.example.time.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LeaveServiceImplementation implements LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Override
    public LeaveRequest applyLeave(LeaveRequest request) {
        request.setStatus("PENDING");
        request.setAppliedOn(LocalDateTime.now());
        return leaveRequestRepository.save(request);
    }

    @Override
    public LeaveRequest approveLeave(Long leaveRequestId, Long approverId) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        LeaveBalance leaveBalance = leaveBalanceRepository
                .findByEmployeeAndLeaveTypeId(
                        leaveRequest.getEmployee(), leaveRequest.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("Leave balance missing"));

        leaveBalance.setUsedLeaves(leaveBalance.getUsedLeaves() + leaveRequest.getTotalDays());
        leaveBalance.setRemainingLeaves(
                leaveBalance.getTotalLeaves() - leaveBalance.getUsedLeaves());

        leaveRequest.setStatus("APPROVED");
        leaveRequest.setApprovedBy(approverId);

        leaveBalanceRepository.save(leaveBalance);
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public LeaveRequest rejectLeave(Long leaveRequestId, Long approverId) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        leaveRequest.setStatus("REJECTED");
        leaveRequest.setApprovedBy(approverId);
        return leaveRequestRepository.save(leaveRequest);
    }

}
