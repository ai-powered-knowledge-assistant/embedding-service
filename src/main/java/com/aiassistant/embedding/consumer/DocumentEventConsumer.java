package com.aiassistant.embedding.consumer;

import com.aiassistant.embedding.dto.DocumentUploadedEvent;
import com.aiassistant.embedding.service.EmbeddingPipeline;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class DocumentEventConsumer {

    private final EmbeddingPipeline embeddingPipeline;

    @KafkaListener(topics = "document.uploaded")
    public void consume(DocumentUploadedEvent event) {
        log.info("Kafka event received for document {}", event.getDocumentId());

        embeddingPipeline.process(event);
    }
}

