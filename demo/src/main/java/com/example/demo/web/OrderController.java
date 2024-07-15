package com.example.demo.web;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.TacoOrder;
import com.example.demo.Users;
import com.example.demo.data.OrderRepository;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
  private OrderRepository orderRepo;
  public OrderController(OrderRepository orderRepo){
    this.orderRepo=orderRepo;
  }
  @GetMapping("/current")
  public String orderForm() {
    return "orderForm";
  }
  @PostMapping
  public String processOrder(
          @Valid TacoOrder order,Errors errors,
          SessionStatus sessionStatus,
          @AuthenticationPrincipal Users user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }
    order.setUser(user);
    orderRepo.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }

}