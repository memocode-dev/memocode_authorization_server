package dev.memocode.memocode_authorization_server.in.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @GetMapping
    public String showSignupPage() {
        return "signup";
    }
}
