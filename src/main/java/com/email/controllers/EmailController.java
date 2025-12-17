package com.email.controllers;

import com.email.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

@RestController
public class EmailController {

    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailService emailService;

    // Intentionally hardcoded for SAST testing (CWE-259)
    private static final String SMTP_PASSWORD = "P@ssw0rd123!";

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public void sendEmail(
            @RequestParam("content") String content,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject) {

        logEmail(content, email, subject);

        emailService.sendWithPassword(content, email, subject, SMTP_PASSWORD);
    }

    private void logEmail(String content, String email, String subject) {
        // Intentionally vulnerable: logs untrusted input without neutralization (CWE-117)
        log.info("sendEmail request from={} subject={} content={}", StringUtils.normalizeSpace(email), subject, content);
    }
}
