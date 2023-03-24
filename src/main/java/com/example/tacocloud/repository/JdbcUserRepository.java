package com.example.tacocloud.repository;

import com.example.tacocloud.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {
    private static final String DELETE_USER = "delete from users where id = ?";
    private static final String INSERT_INTO_USER = "insert into users (user_name, user_password, full_name, street, city, user_state, zip, phone_number) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "update users set user_name = ?, user_password = ?, full_name = ?, street = ?, city = ?, user_state = ?, zip = ?, phone_number = ? where id = ?";
    private static final String FIND_BY_ID = "select * from users where id = ?";
    private static final String FIND_BY_NAME = "select * from users where user_name = ?";

    private final JdbcTemplate jdbcTemplate;
    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        jdbcTemplate.update(INSERT_INTO_USER,
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                user.getStreet(),
                user.getCity(),
                user.getState(),
                user.getZip(),
                user.getPhoneNumber()
            );
        return user;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public Boolean delete(User user) {
        return jdbcTemplate.update(DELETE_USER, user.getId()) == 1;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public Boolean update(User user) {
        return jdbcTemplate.update(UPDATE_USER, user.getId()) == 1;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, User.class, id);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        List<User> results = jdbcTemplate.query(FIND_BY_NAME, this::mapRowToUser, username);
        return results.size() == 0 ?
                new User() :
                results.get(0);
    }

    private User mapRowToUser(ResultSet row, int rowNum) {
        try {
            return new User(
                    row.getLong("id"),
                    row.getString("user_name"),
                    row.getString("user_password"),
                    row.getString("full_name"),
                    row.getString("street"),
                    row.getString("city"),
                    row.getString("user_state"),
                    row.getString("zip"),
                    row.getString("phone_number")
            );
        } catch (SQLException e) {
            log.error("Ошибка получения модели User из БД {}", e.getMessage());
            return new User();
        }
    }
}
