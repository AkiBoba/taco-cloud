package com.example.tacocloud.web.repository;

import com.example.tacocloud.domain.User;

public interface UserRepository {
    User save(User user);
    Boolean delete(User user);
    Boolean update(User user);
    User findById(Long id);
    User findByUsername(String username);
}
