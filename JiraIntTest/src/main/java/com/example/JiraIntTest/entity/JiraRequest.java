package com.example.JiraIntTest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "jira_requests")
public class JiraRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Jira ID is required")
    @Pattern(regexp = "^V2D[0-9A-Z]+$", message = "Jira ID must start with V2D")
    @Column(name = "jira_id")
    private String jiraId;

    @NotBlank(message = "Auth token is required")
    @Pattern(regexp = "^Bearer\\s+.+$", message = "Auth must start with Bearer")
    @Column(name = "auth_token")
    private String authToken;

    // Constructors
    public JiraRequest() {}

    public JiraRequest(String jiraId, String authToken) {
        this.jiraId = jiraId;
        this.authToken = authToken;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJiraId() { return jiraId; }
    public void setJiraId(String jiraId) { this.jiraId = jiraId; }

    public String getAuthToken() { return authToken; }
    public void setAuthToken(String authToken) { this.authToken = authToken; }
}
