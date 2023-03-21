package com.example.tacocloud.repository;

import com.example.tacocloud.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {
    private static final String DELETE_USER = "delete from user where id = ?";
    private static final String INSERT_INTO_USER = "insert into user (id, user_name, user_password, full_name, street, city, user_state, zip, phone_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "update user set user_name = ?, user_password = ?, full_name = ?, street = ?, city = ?, user_state = ?, zip = ?, phone_number = ? where id = ?";
    private static final String FIND_BY_ID = "select * from user where id = ?";

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
                user.getId(),
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
        return jdbcTemplate.queryForObject(FIND_BY_ID, User.class);
    }

    /**
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        return null;
    }
}
