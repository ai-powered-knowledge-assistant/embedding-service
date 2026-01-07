package com.aiassistant.embedding.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "document.storage")
@Getter
@Setter
public class DocumentStorageProperties {
    private String basePath;
}

