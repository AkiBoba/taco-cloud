package com.example.tacocloud.web;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@SessionAttributes("tacoOrder")
@RestController
public class RestTacoController {

    @PostMapping("/ordersadd")
    public ResponseEntity processTaco(@RequestBody @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Ingredient> ingredientList = taco.getIngredients();
        Boolean b = StringUtils.isNotBlank(ingredientList.get(2).getId());
        log.info(b.toString());
        ingredientList.stream().filter(ingredient -> !StringUtils.isNotBlank(ingredient.getId())).collect(Collectors.toList());
        taco.setIngredients(ingredientList);
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}