package com.example.time.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalTime;


@Entity
@Table(name = "shift")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftId;

    private String shiftName;
    private LocalTime startTime;
    private LocalTime endTime;

    private Integer workingDays;
}
