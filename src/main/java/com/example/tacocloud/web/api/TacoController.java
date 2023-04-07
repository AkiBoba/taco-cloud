package com.example.tacocloud.web.api;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.repository.OrderRepository;
import com.example.tacocloud.repository.TacoPepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TacoController {

    private final TacoPepository tacoPepository;
    private final OrderRepository orderRepository;

    @GetMapping("/recent")
    public List<Taco> recentTacos() {
        return tacoPepository.findAll();
    }

    @GetMapping("/recent/{id}")
    public List<Ingredient> tacoById(@PathVariable("id") Long id) {
        return tacoPepository.findById(id);
    }
}
