package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.Users;
import com.example.demo.data.UserRepository;



@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }



  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> {
      Users user = userRepo.findByUsername(username);
      if (user != null) return user;

      throw new UsernameNotFoundException("User '" + username + "' not found");
    };
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests()
        .requestMatchers("/design", "/orders").hasRole("USER")
        .requestMatchers("/","/**")
        .permitAll()
  

        .and()
          .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/design",true)

      .and()
        .oauth2Login()
          .loginPage("/login")
  

      .and()
        .logout()
          .logoutSuccessUrl("/")
      .and()
        .build();
  }


}
