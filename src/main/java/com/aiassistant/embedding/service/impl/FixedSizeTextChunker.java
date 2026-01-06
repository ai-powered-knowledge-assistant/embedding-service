package com.aiassistant.embedding.service.impl;

import com.aiassistant.embedding.service.TextChunker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class FixedSizeTextChunker implements TextChunker {

    private static final int CHUNK_SIZE = 500;

    @Override
    public List<String> chunk(String text) {

        log.info("Chunking text of length {}", text.length());


        List<String> chunks = new ArrayList<>();

        for (int i = 0; i < text.length(); i += CHUNK_SIZE) {
            chunks.add(text.substring(i, Math.min(text.length(), i + CHUNK_SIZE)));
        }
        return chunks;
    }
}
