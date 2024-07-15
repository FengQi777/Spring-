package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Users;

@Controller
@RequestMapping("/login")
public class LoginController {
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    public LoginController(
        PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
      this.passwordEncoder = passwordEncoder;
      this.userDetailsService=userDetailsService;
    }
    @GetMapping
    public String loginForm() {
        return "login";
    }

    @PostMapping
    public String processRegistration(LoginForm form) {
        Users user=(Users)userDetailsService.loadUserByUsername(form.getUsername());
        if (passwordEncoder.encode(form.getPassword()).equals(user  .getPassword())) {
            return "redirect:design";
        }
        else{
            return "login";
        }

    }
}
