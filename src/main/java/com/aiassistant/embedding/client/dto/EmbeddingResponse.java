package com.aiassistant.embedding.client.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddingResponse {

    private List<Data> data;

    public static class Data {
        private List<Float> embedding;
        public List<Float> getEmbedding() { return embedding; }
    }
}
