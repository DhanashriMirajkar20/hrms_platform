package com.example.hrms_platform_document.repository;

import com.example.hrms_platform_document.entity.DocumentAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAuditRepository extends JpaRepository<DocumentAudit, Long> {
}
