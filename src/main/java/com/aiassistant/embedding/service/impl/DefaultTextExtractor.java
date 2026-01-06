package com.aiassistant.embedding.service.impl;

import com.aiassistant.embedding.service.TextExtractor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultTextExtractor implements TextExtractor {

    @Override
    public String extractText(String documentId) {
        log.warn("Text extraction not implemented yet for document {}. Returning placeholder text.", documentId);

        // TODO: Replace this with real document loading and text extraction.
        return "Placeholder text for document " + documentId;
    }
}

