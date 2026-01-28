package com.example.hrms_platform_document.entity;

import com.example.EmployeeManagement.Model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_access_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentAccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String action;
    private LocalDateTime accessTime;
    private String ipAddress;

    // getters & setters
}

