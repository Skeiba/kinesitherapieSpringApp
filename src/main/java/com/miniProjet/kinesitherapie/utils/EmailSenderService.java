package com.miniProjet.kinesitherapie.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public boolean sendPasswordResetEmail(String email, String subject, String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailMessage.setFrom(senderEmail);

            mailSender.send(mailMessage);
            log.info("Password reset email sent to {}", email);
            return true;
        } catch (MailException e) {
            log.error("Failed to send email to {}: {}", email, e.getMessage());
            return false;
        }
    }
}
