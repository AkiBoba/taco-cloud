package com.example.tacocloud.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Класс содержит поля идентифицирующие характеристики Тако
 * @Ingredient
 */
@Data
@NoArgsConstructor
public class Taco {
    @Id
    private Long id;
    private Date createdAt = new Date();
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;
    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;


    public Taco(Long id, Date createdAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }
}