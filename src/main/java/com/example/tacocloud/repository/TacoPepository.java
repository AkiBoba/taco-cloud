package com.example.tacocloud.repository;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;

import java.util.List;

public interface TacoPepository {
    List<Taco> findAll();

    List<Ingredient> findById(Long id);
}
