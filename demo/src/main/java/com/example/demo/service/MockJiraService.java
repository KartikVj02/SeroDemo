package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class MockJiraService {

	// Support java 17
	/*
	 * public String getIssue(String issueId) {
	 * 
	 * return """ { "key": "%s", "summary": "Demo Jira Issue", "description":
	 * "This is a mocked Jira issue used for demo", "status": "IN PROGRESS",
	 * "assignee": "Demo User" } """.formatted(issueId); }
	 */

	// same for java 8
	public String getIssue(String issueId) {

		return String.format(
	            "{\n" +
	            "  \"key\": \"%s\",\n" +
	            "  \"summary\": \"Demo Jira Issue\",\n" +
	            "  \"description\": \"This is a mocked Jira issue used for demo\",\n" +
	            "  \"status\": \"IN PROGRESS\",\n" +
	            "  \"assignee\": \"Demo User\"\n" +
	            "}",
	            issueId
	    );
	}
}
