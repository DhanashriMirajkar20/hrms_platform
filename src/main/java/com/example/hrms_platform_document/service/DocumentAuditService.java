package com.example.hrms_platform_document.service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.hrms_platform_document.entity.Document;
import com.example.hrms_platform_document.entity.DocumentAudit;
import com.example.hrms_platform_document.entity.DocumentVersion;
import com.example.hrms_platform_document.repository.DocumentAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DocumentAuditService {

    @Autowired
    private DocumentAuditRepository auditRepo;

    public void log(Document doc, DocumentVersion version, String action, Employee emp) {
        DocumentAudit audit = new DocumentAudit();
        audit.setDocument(doc);
        audit.setVersion(version);
        audit.setAction(action);
        audit.setPerformedBy(emp);
        audit.setPerformedAt(LocalDateTime.now());
        auditRepo.save(audit);
    }
}
