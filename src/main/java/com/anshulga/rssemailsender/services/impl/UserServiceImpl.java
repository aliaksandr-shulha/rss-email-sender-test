package com.anshulga.rssemailsender.services.impl;

import com.anshulga.rssemailsender.entities.User;
import com.anshulga.rssemailsender.entities.UserDto;
import com.anshulga.rssemailsender.factories.UserFactory;
import com.anshulga.rssemailsender.repositories.UserRepository;
import com.anshulga.rssemailsender.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserFactory factory;

    public UserServiceImpl(final UserRepository userRepository, UserFactory factory) {
        this.repository = userRepository;
        this.factory = factory;
    }

    @Override
    public UserDto save(UserDto userDto) {
        User newUser = repository.findByEmail(userDto.getEmail());
        if (newUser == null){
            newUser = factory.getUser(userDto);
            newUser = repository.save(newUser);
        }
        return factory.getDto(newUser);
    }

}
