package com.example.hrms_platform_document.repository;

import com.example.hrms_platform_document.entity.DocumentAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAccessLogRepository extends JpaRepository<DocumentAccessLog, Long> {
}
