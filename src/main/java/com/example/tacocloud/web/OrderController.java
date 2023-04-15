package com.example.tacocloud.web;

import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.domain.User;
import com.example.tacocloud.props.OrderProps;
import com.example.tacocloud.web.repository.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    private OrderRepository orderRepo;
    private OrderProps orderProps;
    public OrderController(OrderRepository orderRepo, OrderProps orderProps) {
        this.orderRepo = orderRepo;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * Этот метод регистрирует объект TacoOrder в журнале
     * и завершает сеанс
     * @param order объект TacoOrder
     * @param sessionStatus объект SessionStatus
     * @param user объект User
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        order.setPlacedAt(new Date());
        Long orderId = orderRepo.save(order).getId();
        orderRepo.saveUser(user, orderId);
        log.info("Order saved: {}", order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String findByUserOrders(
            @AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders",
                orderRepo.findByUserOrders(user, orderProps.getPageSize()));
        return "orderList";
    }
}
