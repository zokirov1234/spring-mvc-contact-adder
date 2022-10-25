package com.example.contact_adder.controller;

import com.example.contact_adder.model.dto.UserReceiveDto;
import com.example.contact_adder.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String sendRegister() {
        return "register";
    }

    @PostMapping("/register/user")
    public String register(
            @ModelAttribute UserReceiveDto userReceiveDto
    ) {

        userService.save(userReceiveDto);
        return "login";
    }
}
