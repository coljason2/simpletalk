package com.simpletalk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegisterController {

    @PostMapping
    public String register(@RequestParam("username") String username, HttpSession session) {
        log.info("username={}", username);

        if (username != null && !username.trim().isEmpty()) {
            session.setAttribute("username", username.trim());
        }
        return "chat";
    }

    @GetMapping
    public String redirectToChat(HttpSession session) {
        Object username = session.getAttribute("username");

        log.info("username={}", username);

        if (username == null) {
            return "redirect:/";
        }
        return "chat";
    }
}
