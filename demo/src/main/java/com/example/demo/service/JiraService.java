package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.example.demo.exception.InvalidPrefixException;



@Service
public class JiraService {

    private final MockJiraService mockJiraService;

    public JiraService(MockJiraService mockJiraService) {
        this.mockJiraService = mockJiraService;
    }

    public byte[] getIssuePdf(String issueId) {

        if (!issueId.startsWith("v2d")) {
            throw new InvalidPrefixException(
                    "Only issue IDs starting with 'v2d' are allowed");
        }

        String jiraResponse = mockJiraService.getIssue(issueId);
        return generatePdf(issueId, jiraResponse);
    }

    private byte[] generatePdf(String issueId, String content) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();
            document.add(new Paragraph("JIRA ISSUE REPORT"));
            document.add(new Paragraph("Issue ID: " + issueId));
            document.add(new Paragraph("Generated At: " + LocalDateTime.now()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Mock Jira Response:"));
            document.add(new Paragraph(content));
            document.close();

            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }
    }
}
