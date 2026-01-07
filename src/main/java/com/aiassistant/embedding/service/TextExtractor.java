package com.aiassistant.embedding.service;

import java.nio.file.Path;

public interface TextExtractor {
    String extractText(Path filePath);
}

