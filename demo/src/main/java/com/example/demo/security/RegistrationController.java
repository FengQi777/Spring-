package com.example.demo.security;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.data.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  private UserRepository usersRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      UserRepository usersRepo, PasswordEncoder passwordEncoder) {
    this.usersRepo = usersRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    usersRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }
}
