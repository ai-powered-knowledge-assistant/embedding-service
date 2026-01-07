package com.aiassistant.embedding.service.impl;

import com.aiassistant.embedding.service.TextExtractor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@Slf4j
public class PdfTextExtractor implements TextExtractor {

    @Override
    public String extractText(Path filePath) {
        try (PDDocument doc = PDDocument.load(filePath.toFile())) {
            return new PDFTextStripper().getText(doc);
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from " + filePath, e);
        }
    }
}
