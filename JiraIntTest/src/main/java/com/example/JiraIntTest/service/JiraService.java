package com.example.JiraIntTest.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.JiraIntTest.dto.JiraResponseDto;
import com.example.JiraIntTest.entity.JiraRequest;
import com.example.JiraIntTest.repository.JiraRepository;

@Service
public class JiraService {

    private final JiraRepository jiraRepository;
    private final RestTemplate restTemplate;

    @Value("${jira.base-url}")
    private String jiraBaseUrl;

    public JiraService(JiraRepository jiraRepository, RestTemplate restTemplate) {
        this.jiraRepository = jiraRepository;
        this.restTemplate = restTemplate;
    }

    public JiraResponseDto processJiraRequest(JiraRequest request) {

        String jiraUrl = jiraBaseUrl + "/rest/api/3/issue/" + request.getJiraId();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(extractToken(request.getAuthToken()));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response =
                    restTemplate.exchange(jiraUrl, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {

                // Save only on success
                jiraRepository.save(request);

                return new JiraResponseDto(
                        "success",
                        "Jira story found",
                        request.getJiraId(),
                        jiraUrl,
                        generatePdfUrl(request.getJiraId())
                );
            }

            return new JiraResponseDto(
                    "error",
                    "Jira authentication failed",
                    request.getJiraId(),
                    null,
                    null
            );

        } catch (RestClientException ex) {
            return new JiraResponseDto(
                    "error",
                    "Jira API error: " + ex.getMessage(),
                    request.getJiraId(),
                    null,
                    null
            );
        }
    }

    private String extractToken(String authToken) {
        if (authToken == null) {
            throw new IllegalArgumentException("Authorization token missing");
        }
        return authToken.startsWith("Bearer ")
                ? authToken.substring(7)
                : authToken;
    }

    private String generatePdfUrl(String jiraId) {
        return "http://localhost:8080/jira/pdf/" + jiraId;
    }
}
