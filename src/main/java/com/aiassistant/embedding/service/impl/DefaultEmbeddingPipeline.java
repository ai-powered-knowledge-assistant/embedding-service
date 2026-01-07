package com.aiassistant.embedding.service.impl;

import com.aiassistant.embedding.client.EmbeddingClient;
import com.aiassistant.embedding.config.DocumentStorageProperties;
import com.aiassistant.embedding.dto.DocumentUploadedEvent;
import com.aiassistant.embedding.entity.DocumentEmbeddingEntity;
import com.aiassistant.embedding.repository.DocumentEmbeddingRepository;
import com.aiassistant.embedding.service.EmbeddingPipeline;
import com.aiassistant.embedding.service.TextChunker;
import com.aiassistant.embedding.service.TextExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultEmbeddingPipeline implements EmbeddingPipeline {

    private final TextExtractor textExtractor;
    private final TextChunker textChunker;
    private final EmbeddingClient embeddingClient;
    private final DocumentEmbeddingRepository documentEmbeddingRepository;
    private final DocumentStorageProperties storageProperties;


    @Override
    public void process(DocumentUploadedEvent event) {

        log.info("Starting embedding pipeline for document {}", event.getDocumentId());

        Path filePath = Paths.get(
                storageProperties.getBasePath(),
                event.getStoragePath()
        );

        String text = textExtractor.extractText(filePath);


        var chunks = textChunker.chunk(text);

        var embeddings = embeddingClient.embed(chunks);

        log.info("Generated {} embeddings for document {}",
                embeddings.size(),
                event.getDocumentId());

        for (int i = 0; i < embeddings.size(); i++) {

            DocumentEmbeddingEntity entity = new DocumentEmbeddingEntity();
            entity.setId(UUID.randomUUID());
            entity.setDocumentId(event.getDocumentId());
            entity.setChunkIndex(i);
            entity.setContent(chunks.get(i));
            entity.setEmbedding(embeddings.get(i));

            documentEmbeddingRepository.save(entity);
        }

    }
}
