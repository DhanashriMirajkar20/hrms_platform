package com.example.hrms_platform_document.entity;

import java.time.LocalDateTime;

import com.example.EmployeeManagement.Model.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "document")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long documentId;

    private String documentName;
    private String documentType;
    private Boolean isConfidential;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Long currentVersionId;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private Employee uploadedBy;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Employee approvedBy;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters & setters
}
