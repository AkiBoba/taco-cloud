package com.example.tacocloud.domain;

import java.util.List;
import lombok.Data;

/**
 * Класс содержит поля идентифицирующие характеристики Тако
 * @Ingredient
 */
@Data
public class Taco {

    private String name;

    private List<Ingredient> ingredients;

}
