package com.example.tacocloud.repository;

import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository {
    @Transactional
    TacoOrder save(TacoOrder order);

    void saveUser(User user, Long orderId);
}
