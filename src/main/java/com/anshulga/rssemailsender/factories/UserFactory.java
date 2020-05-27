package com.anshulga.rssemailsender.factories;

import com.anshulga.rssemailsender.entities.User;
import com.anshulga.rssemailsender.entities.UserDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserFactory{
    public User getUser(final UserDto userDto) {
        return User.builder()
                .id(UUID.randomUUID())
                .email(userDto.getEmail())
                .rssLinks(userDto.getRssLinks())
                .build();
    }

    public UserDto getDto(final User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .rssLinks(user.getRssLinks())
                .build();
    }
}
