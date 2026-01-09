package com.javatechie.controller;

import com.javatechie.entity.User;
import com.javatechie.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test-db")
    public String testDb() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@test.com");
        user.setPassword("password");

        userService.registerUser(user);
        return "User saved successfully!";
    }
}
