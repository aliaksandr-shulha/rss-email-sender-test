package com.anshulga.rssemailsender.services;

import com.anshulga.rssemailsender.entities.UserDto;

import java.util.List;

public interface UserService {
    UserDto save(UserDto userDto);
}