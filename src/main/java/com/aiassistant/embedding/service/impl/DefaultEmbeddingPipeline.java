package com.aiassistant.embedding.service.impl;

import com.aiassistant.embedding.client.EmbeddingClient;
import com.aiassistant.embedding.dto.DocumentUploadedEvent;
import com.aiassistant.embedding.service.EmbeddingPipeline;
import com.aiassistant.embedding.service.TextChunker;
import com.aiassistant.embedding.service.TextExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultEmbeddingPipeline implements EmbeddingPipeline {

    private final TextExtractor textExtractor;
    private final TextChunker textChunker;
    private final EmbeddingClient embeddingClient;

    @Override
    public void process(DocumentUploadedEvent event) {

        log.info("Starting embedding pipeline for document {}", event.getDocumentId());

        String text = textExtractor.extractText(event.getDocumentId());

        var chunks = textChunker.chunk(text);

        var embeddings = embeddingClient.embed(chunks);

        log.info("Generated {} embeddings for document {}",
                embeddings.size(),
                event.getDocumentId());
    }
}
