package com.example.JiraIntTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JiraIntTest.dto.JiraResponseDto;
import com.example.JiraIntTest.entity.JiraRequest;
import com.example.JiraIntTest.service.JiraService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jira")
public class JiraController {

	@Autowired
    private final JiraService jiraService;

    public JiraController(JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @PostMapping("/generate-pdf")
    public ResponseEntity<JiraResponseDto> generatePdf(
            @Valid @RequestBody JiraRequest request) {

        JiraResponseDto response = jiraService.processJiraRequest(request);
        return ResponseEntity.ok(response);
    }
}
