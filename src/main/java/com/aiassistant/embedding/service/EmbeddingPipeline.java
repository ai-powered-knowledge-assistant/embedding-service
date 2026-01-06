package com.aiassistant.embedding.service;

import com.aiassistant.embedding.dto.DocumentUploadedEvent;

public interface EmbeddingPipeline {

    void process(DocumentUploadedEvent event);
}
