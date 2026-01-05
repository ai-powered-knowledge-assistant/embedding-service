package com.aiassistant.embedding.consumer;

import com.aiassistant.embedding.dto.DocumentUploadedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DocumentEventConsumer {

    @KafkaListener(
            topics = "document.uploaded",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(DocumentUploadedEvent event) {

        log.info("📄 Document received for embedding: {}", event.getDocumentId());

        // Next step: text extraction → chunking → embeddings
    }
}
