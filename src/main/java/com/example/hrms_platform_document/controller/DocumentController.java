package com.example.hrms_platform_document.controller;
import com.example.hrms_platform_document.service.DocumentService;
import com.example.hrms_platform_document.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/api/v1/hrms/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    public ResponseEntity<String> upload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("documentName") String documentName,
            @RequestPart("documentType") String documentType,
            @RequestPart("employeeId") Long employeeId
    ) throws IOException {

        documentService.uploadDocument(
                file,
                documentName,
                documentType,
                employeeId
        );

        return ResponseEntity.ok("Document uploaded successfully");
    }

    @GetMapping("/{documentId}/download")
    public ResponseEntity<byte[]> download(@PathVariable Long documentId) {

        Document doc = documentService.getDocument(documentId);
        byte[] fileData = documentService.downloadDocument(documentId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + doc.getDocumentName() + "\""
                )
                .contentLength(fileData.length)
                .body(fileData);
    }
}
