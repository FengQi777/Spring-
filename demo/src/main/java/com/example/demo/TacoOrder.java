package com.example.demo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class TacoOrder implements Serializable{
  private static final long serialVersionID=1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Date placedAt;


  @NotBlank(message = "Delivery name is required")
  private String deliveryName;

  @NotBlank(message = "Delivery street is required")
  private String deliveryStreet;

  @NotBlank(message = "Delivery city is required")
  private String deliveryCity;

  @NotBlank(message = "Delivery state is required")
  private String deliveryState;

  @NotBlank(message = "Delivery zip is required")
  private String deliveryZip;

  @CreditCardNumber(message = "not a valid credit card number")
  private String ccNumber;

  @Pattern(regexp="^(0[1-9])|1(0-2)([\\/])([2-9][0-9])$",message = "must be formatted MM/YY")
  private String ccExpiration;

  @Digits(integer = 3,fraction = 0,message = "Invalid CVV")
  private String ccCVV;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Taco> tacos = new ArrayList<>();

  @ManyToOne
  private Users user;


  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }
}