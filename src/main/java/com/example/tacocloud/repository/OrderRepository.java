package com.example.tacocloud.repository;

import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderRepository {
    @Transactional
    TacoOrder save(TacoOrder order);

    void saveUser(User user, Long orderId);

    List<TacoOrder> findByUserOrders(User user, Integer pageable);

    void deleteById(Long orderId);
}
