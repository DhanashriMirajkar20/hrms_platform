package com.example.time.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class ShiftDTO {

    private Long shiftId;

    private String shiftName;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer workingDays;
}
