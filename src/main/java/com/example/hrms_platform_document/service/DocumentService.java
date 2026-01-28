package com.example.hrms_platform_document.service;

import com.example.EmployeeManagement.Model.Employee;
import com.example.hrms_platform_document.util.ChecksumUtil;
import com.example.EmployeeManagement.Repository.EmployeeRepository;
import com.example.hrms_platform_document.entity.Document;
import com.example.hrms_platform_document.entity.DocumentVersion;
import com.example.hrms_platform_document.repository.DocumentRepository;
import com.example.hrms_platform_document.repository.DocumentVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class DocumentService {

    private DocumentRepository documentRepo;

    private DocumentVersionRepository versionRepo;

    private EmployeeRepository employeeRepo;

    private DocumentAuditService auditService;

    @Transactional
    public void uploadDocument(
            MultipartFile file,
            String name,
            String type,
            Long employeeId
    ) throws IOException {

        // ✅ FETCH MANAGED EMPLOYEE


        Employee emp = employeeRepo.findById(employeeId)
                .orElseThrow(() ->
                );

        Document document = new Document();
        document.setDocumentName(name);
        document.setDocumentType(type);
        document.setEmployee(emp);
        document.setUploadedBy(emp);
        document.setCreatedAt(LocalDateTime.now());

        documentRepo.save(document);

        DocumentVersion version = new DocumentVersion();
        version.setDocument(document);
        version.setFileData(file.getBytes());
        version.setVersionNumber(1);
        version.setChecksum(ChecksumUtil.generate(file.getBytes()));
        version.setUploadedBy(emp);
        version.setUploadedAt(LocalDateTime.now());

        versionRepo.save(version);

        document.setCurrentVersionId(version.getVersionId());
        documentRepo.save(document);

        auditService.log(document, version, "UPLOAD", emp);
    }

    public byte[] downloadDocument(Long documentId) {

        Document doc = documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        DocumentVersion version = versionRepo.findById(doc.getCurrentVersionId())
                .orElseThrow(() -> new RuntimeException("Document version not found"));

        return version.getFileData();
    }

    public Document getDocument(Long documentId) {
        return documentRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

}
