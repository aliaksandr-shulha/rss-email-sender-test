package com.anshulga.rssemailsender.factories.impl;

import com.anshulga.rssemailsender.entities.User;
import com.anshulga.rssemailsender.factories.MailFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailFactoryImpl implements MailFactory {
    @Value("${support.email}")
    private String supportEmail;
    private final JavaMailSender mailSender;

    public MailFactoryImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public MimeMessage getMail(final User user,
                               final String message) {
        final String recipientAddress = user.getEmail();
        final MimeMessage email = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(email, true);
            String subject = "Rss service";
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setFrom(supportEmail);
            helper.setTo(recipientAddress);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return email;
    }
}
