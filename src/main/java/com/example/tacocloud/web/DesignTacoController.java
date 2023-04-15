package com.example.tacocloud.web;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Ingredient.Type;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.web.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /**
     * Этот метод используется для добавления в модель атрибутов со значениями ингредиентов
     * @param model контейнер в котором будут сохранены аргументы для отправки их веб-представлению.
     */
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/design")
    public String showDesignForm() {
        log.info("showDesignForm");
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> list = new ArrayList<>();
        ingredients.forEach(list::add);
        return list
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @GetMapping("/gotocurrentorder")
    public String processTaco(@ModelAttribute TacoOrder tacoOrder) {
        log.info("Go to current order");
        return "redirect:/orders/current";
    }

}
