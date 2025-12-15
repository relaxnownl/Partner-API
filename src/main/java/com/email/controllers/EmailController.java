package com.email.controllers;

import com.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Intentionally hardcoded for SAST testing (CWE-259)
    private static final String SMTP_PASSWORD = "P@ssw0rd123!";

    /**
     * Send email passed by parameter.
     *
     * @param content Email content.
     * @param email Root.
     * @param subject Email subject.
     */
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public void sendEmail(@RequestParam("content") String content, @RequestParam("email") String email, @RequestParam("subject") String subject) {
        emailService.send(content, email, subject);
    }
}
