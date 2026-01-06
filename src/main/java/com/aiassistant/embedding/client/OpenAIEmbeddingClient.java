package com.aiassistant.embedding.client;

import com.aiassistant.embedding.client.dto.EmbeddingRequest;
import com.aiassistant.embedding.client.dto.EmbeddingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAIEmbeddingClient implements EmbeddingClient {

    @Value("${openai.api-key}")
    private String apiKey;

    @Value("${openai.base-url}")
    private String baseUrl;

    @Value("${embedding.model}")
    private String model;

    private final RestTemplate restTemplate;

    @Override
    public List<float[]> embed(List<String> chunks) {

        try {
            EmbeddingRequest request = new EmbeddingRequest(model, chunks);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            HttpEntity<EmbeddingRequest> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<EmbeddingResponse> response =
                    restTemplate.postForEntity(
                            baseUrl + "/embeddings",
                            entity,
                            EmbeddingResponse.class
                    );

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new IllegalStateException(
                        "OpenAI returned non-200 status: " + response.getStatusCode()
                );
            }

            EmbeddingResponse body = response.getBody();

            if (body == null || body.getData() == null) {
                throw new IllegalStateException("Empty embedding response from OpenAI");
            }

            List<float[]> vectors = new ArrayList<>();

            for (EmbeddingResponse.Data d : body.getData()) {
                float[] vector = new float[d.getEmbedding().size()];
                for (int i = 0; i < vector.length; i++) {
                    vector[i] = d.getEmbedding().get(i);
                }
                vectors.add(vector);
            }

            log.info("Generated {} real embeddings", vectors.size());
            log.info("Embedding vector size: {}", vectors.get(0).length);

            return vectors;

        } catch (RestClientException ex) {
            log.error("OpenAI API call failed", ex);
            throw ex; // Kafka retry will handle this
        }
    }
}
