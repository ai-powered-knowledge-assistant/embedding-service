package com.aiassistant.embedding.repository;

import com.aiassistant.embedding.entity.DocumentEmbeddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentEmbeddingRepository extends JpaRepository<DocumentEmbeddingEntity, UUID> {

    @Query(
            value = """
    SELECT content
    FROM document_embedding
    ORDER BY embedding <=> :queryEmbedding
    LIMIT :topK
  """,
            nativeQuery = true
    )
    List<String> findTopK(
            @Param("queryEmbedding") float[] queryEmbedding,
            @Param("topK") int topK
    );

}
