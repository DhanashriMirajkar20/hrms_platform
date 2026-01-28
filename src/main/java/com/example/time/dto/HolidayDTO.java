package com.example.time.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class HolidayDTO {

    private long holidayId;
    private LocalDate holidayDate;
    private String occasion;
    private String location;
    private boolean optional;

}
