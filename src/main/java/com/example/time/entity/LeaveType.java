package com.example.time.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "leave_type")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveTypeId;

    @Column(nullable = false, unique = true)
    private String leaveName;

    private Integer maxPerYear;
    private Boolean carryForwardAllowed;
}
