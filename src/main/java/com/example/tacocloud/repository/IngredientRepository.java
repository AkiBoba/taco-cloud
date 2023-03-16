package com.example.tacocloud.repository;

import com.example.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
