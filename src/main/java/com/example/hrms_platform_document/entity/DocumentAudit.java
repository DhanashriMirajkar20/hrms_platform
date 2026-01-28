package com.example.hrms_platform_document.entity;

import com.example.EmployeeManagement.Model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne
    @JoinColumn(name = "version_id")
    private DocumentVersion version;

    private String action;

    @ManyToOne
    @JoinColumn(name = "performed_by")
    private Employee performedBy;

    private LocalDateTime performedAt;
    private String remarks;

    // getters & setters
}
