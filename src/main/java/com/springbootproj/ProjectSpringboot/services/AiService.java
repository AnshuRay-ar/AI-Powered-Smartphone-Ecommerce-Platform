
package com.springbootproj.ProjectSpringboot.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.model:gemini-3.8-flash}")
    private String model;

    @Autowired
    private RestTemplate restTemplate;

    public String ask(String systemPrompt,
                      String userMessage) {

        try {

            String url =
                "https://generativelanguage.googleapis.com/v1beta/models/"
                + model + ":generateContent";

            HttpHeaders headers =
                    new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_JSON);

            headers.set("x-goog-api-key", apiKey);

            ObjectMapper mapper =
                    new ObjectMapper();

            Map<String, Object> body = Map.of(

                "systemInstruction",
                Map.of(
                    "parts",
                    List.of(
                        Map.of("text", systemPrompt)
                    )
                ),

                "contents",
                List.of(
                    Map.of(
                        "role", "user",
                        "parts",
                        List.of(
                            Map.of("text", userMessage)
                        )
                    )
                )
            );

            String jsonBody =
                    mapper.writeValueAsString(body);

            HttpEntity<String> request =
                    new HttpEntity<>(jsonBody, headers);

            System.out.println("Sending request to Gemini...");
            System.out.println("Model: " + model);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            request,
                            String.class
                    );

            System.out.println("Gemini response received.");

            JsonNode root =
                    mapper.readTree(response.getBody());

            JsonNode candidates =
                    root.path("candidates");

            if (!candidates.isArray() ||
                candidates.isEmpty()) {

                return "ERROR: Gemini did not return a response.";
            }

            JsonNode parts =
                    candidates.get(0)
                            .path("content")
                            .path("parts");

            StringBuilder result =
                    new StringBuilder();

            for (JsonNode part : parts) {

                if (part.has("text")) {
                    result.append(part.get("text").asText());
                }
            }

            if (result.isEmpty()) {
                return "ERROR: Gemini returned an empty response.";
            }

            return result.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR: " + e.getMessage();
        }
    }
}
