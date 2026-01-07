package com.aiassistant.embedding.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "document_embedding")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentEmbeddingEntity {

    @Id
    private UUID id;

    private String documentId;

    private int chunkIndex;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "vector(384)")
    private float[] embedding;
}
