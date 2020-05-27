package com.anshulga.rssemailsender.controllers;

import com.anshulga.rssemailsender.entities.UserDto;
import com.anshulga.rssemailsender.services.RssService;
import com.anshulga.rssemailsender.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RssController {
    private final UserService userService;
    private final RssService rssService;

    public RssController(UserService userService, RssService rssService) {
        this.userService = userService;
        this.rssService = rssService;
    }

    @PostMapping("/saveUser")
    private ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.save(userDto));
    }

    @PostMapping("/save")
    private ResponseEntity<String> saveRssLink(@RequestBody UserDto userDto){
        return ResponseEntity.ok(rssService.saveRssLink(userDto));
    }

    @DeleteMapping("/remove")
    private ResponseEntity removeRssLinks(@RequestBody UserDto userDto){
        return ResponseEntity.ok(rssService.removeLinks(userDto));
    }

    @PostMapping("/send")
    private ResponseEntity<String> sendMail(@RequestBody UserDto user){
        return ResponseEntity.ok(rssService.send(user));
    }
}
