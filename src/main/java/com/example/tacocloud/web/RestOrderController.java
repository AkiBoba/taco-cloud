package com.example.tacocloud.web;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.web.repository.IngredientRepository;
import com.example.tacocloud.web.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SessionAttributes("tacoOrder")
@RestController
@CrossOrigin(origins="http://tacocloud:8080")
@RequiredArgsConstructor
public class RestOrderController {

    private final IngredientRepository ingredientRepository;
    private final OrderRepository orderRepository;

    @PostMapping("/orderadd")
    public ResponseEntity<?> processTaco(@RequestBody @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Ingredient> ingredientList = new ArrayList<>();
        taco.getIngredients().forEach(ingredient -> {
            if (ingredient.getId() != null && ingredientRepository.findById(ingredient.getId()).isPresent()) {
                ingredientList.add(ingredient);
            }
        });
        taco.setIngredients(ingredientList);
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delorder/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (Exception e) {
            log.error("SQL error {}", e.getMessage());
        }
    }
}