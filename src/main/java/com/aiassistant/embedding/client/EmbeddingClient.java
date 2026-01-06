package com.aiassistant.embedding.client;

import java.util.List;

public interface EmbeddingClient {

    List<float[]> embed(List<String> chunks);
}
