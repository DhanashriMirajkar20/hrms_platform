package com.example.time.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class LeaveRequestDTO {

    private long employeeId;
    private long leaveTypeId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int totalDays;

}
