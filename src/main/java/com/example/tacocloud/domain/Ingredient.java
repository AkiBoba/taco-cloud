package com.example.tacocloud.domain;

import lombok.Data;

/**
 * Класс содержит поля идентифицирующие характеристики ингредиентов
 */
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
