package com.anshulga.rssemailsender.services;

import com.anshulga.rssemailsender.entities.UserDto;

import java.util.Set;

public interface RssService {
    String saveRssLink(UserDto userDto);
    String send(UserDto userDto);
    Set<String> removeLinks(UserDto userDto);
}
