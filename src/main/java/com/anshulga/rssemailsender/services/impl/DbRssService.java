package com.anshulga.rssemailsender.services.impl;

import com.anshulga.rssemailsender.entities.User;
import com.anshulga.rssemailsender.entities.UserDto;
import com.anshulga.rssemailsender.factories.MailFactory;
import com.anshulga.rssemailsender.factories.RssMessageFactory;
import com.anshulga.rssemailsender.repositories.UserRepository;
import com.anshulga.rssemailsender.services.RssService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class DbRssService implements RssService {
    private final UserRepository repository;
    private final MailFactory mailFactory;
    private final RssMessageFactory rssMessageFactory;
    private final JavaMailSender mailSender;

    public DbRssService(UserRepository repository,
                        MailFactory mailFactory,
                        RssMessageFactory rssMessageFactory,
                        JavaMailSender mailSender) {
        this.repository = repository;
        this.mailFactory = mailFactory;
        this.rssMessageFactory = rssMessageFactory;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional
    public String saveRssLink(final UserDto userDto) {
        User user = repository.findByEmail(userDto.getEmail());
        user.getRssLinks().addAll(userDto.getRssLinks());
        return (String) user.getRssLinks().toArray()[0];
    }


    @Override
    public String send(final UserDto userDto) {
        User user = repository.findByEmail(userDto.getEmail());
        String message = rssMessageFactory.getRssMessage(user.getRssLinks());
        mailSender.send(mailFactory.getMail(user, message));
        return message;
    }

    @Override
    @Transactional
    public Set<String> removeLinks(UserDto userDto) {
        User user = repository.findByEmail(userDto.getEmail());
        user.getRssLinks().removeAll(userDto.getRssLinks());
        return userDto.getRssLinks();
    }

}
