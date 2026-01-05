package com.aiassistant.embedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentUploadedEvent {

    private String documentId;
    private String fileName;
    private String contentType;
    private long fileSize;
    private Instant uploadedAt;

}
