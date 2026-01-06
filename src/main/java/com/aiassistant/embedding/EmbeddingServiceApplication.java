package com.aiassistant.embedding;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmbeddingServiceApplication {

	public static void main(String[] args) {
		// Load .env file before Spring Boot starts
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();
		
		// Set system properties from .env file so Spring can access them via ${VAR}
		dotenv.entries().forEach(entry -> {
			String key = entry.getKey();
			String value = entry.getValue();
			// Set as system property (Spring Boot reads these for ${} placeholders)
			System.setProperty(key, value);
		});
		
		SpringApplication.run(EmbeddingServiceApplication.class, args);
	}

}
