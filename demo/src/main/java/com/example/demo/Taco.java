package com.example.demo;


import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Entity
@RestResource(rel="tacos",path = "tacos")
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt = new Date();

    @NotNull
    @Size(min = 5,message = "Name must be at least 5 chracter long")
    private String name;
    @ManyToMany
    @Size(min = 1,message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public void addIngredients(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
}
