package com.example.tacocloud.web;

import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@SessionAttributes("tacoOrder")
@RestController
public class RestTacoController {

    @PostMapping("/design")
    public String processTaco(@RequestBody Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}