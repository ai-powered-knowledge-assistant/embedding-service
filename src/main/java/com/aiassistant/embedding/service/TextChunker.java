package com.aiassistant.embedding.service;

import java.util.List;

public interface TextChunker {

    List<String> chunk(String text);
}
