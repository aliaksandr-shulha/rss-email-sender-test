package com.anshulga.rssemailsender.factories;

import com.anshulga.rssemailsender.entities.User;

import javax.mail.internet.MimeMessage;

public interface MailFactory {
    MimeMessage getMail(User user, String message);
}
