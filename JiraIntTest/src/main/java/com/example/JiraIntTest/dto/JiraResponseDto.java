package com.example.JiraIntTest.dto;

public class JiraResponseDto {
    private String status;
    private String message;
    private String jiraId;
    private String storyUrl;
    private String pdfUrl;

    public JiraResponseDto(String status, String message, String jiraId, String storyUrl, String pdfUrl) {
        this.status = status;
        this.message = message;
        this.jiraId = jiraId;
        this.storyUrl = storyUrl;
        this.pdfUrl = pdfUrl;
    }

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getJiraId() { return jiraId; }
    public void setJiraId(String jiraId) { this.jiraId = jiraId; }

    public String getStoryUrl() { return storyUrl; }
    public void setStoryUrl(String storyUrl) { this.storyUrl = storyUrl; }

    public String getPdfUrl() { return pdfUrl; }
    public void setPdfUrl(String pdfUrl) { this.pdfUrl = pdfUrl; }
}