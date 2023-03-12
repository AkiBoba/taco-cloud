package com.example.tacocloud.web;

import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@SessionAttributes("tacoOrder")
@RestController
public class RestTacoController {

    @PostMapping("/ordersadd")
    public ResponseEntity processTaco(@RequestBody @Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}