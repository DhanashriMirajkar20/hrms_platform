package com.example.hrms_platform_document.service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.hrms_platform_document.entity.Document;
import com.example.hrms_platform_document.entity.DocumentAccessLog;
import com.example.hrms_platform_document.repository.DocumentAccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DocumentAccessLogService {

    @Autowired
    private DocumentAccessLogRepository logRepo;

    public void log(Document doc, Employee emp, String action, String ip) {
        DocumentAccessLog log = new DocumentAccessLog();
        log.setDocument(doc);
        log.setEmployee(emp);
        log.setAction(action);
        log.setAccessTime(LocalDateTime.now());
        log.setIpAddress(ip);
        logRepo.save(log);
    }
}

