package com.aiassistant.embedding.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class LocalEmbeddingClient implements EmbeddingClient {

    private final RestTemplate restTemplate;

    @Override
    public List<float[]> embed(List<String> chunks) {

        Map<String, Object> body = Map.of("texts", chunks);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(
                        "http://localhost:8001/embed",
                        body,
                        Map.class
                );

        List<List<Double>> embeddings =
                (List<List<Double>>) response.getBody().get("embeddings");

        List<float[]> vectors = new ArrayList<>();

        for (List<Double> e : embeddings) {
            float[] v = new float[e.size()];
            for (int i = 0; i < e.size(); i++) {
                v[i] = e.get(i).floatValue();
            }
            vectors.add(v);
        }

        log.info("Generated {} local embeddings", vectors.size());
        log.info("Embedding vector size: {}", vectors.get(0).length);

        return vectors;
    }
}
