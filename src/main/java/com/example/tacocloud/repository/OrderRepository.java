package com.example.tacocloud.repository;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.TacoOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderRepository {

    @Transactional
    TacoOrder save(TacoOrder order);
}
