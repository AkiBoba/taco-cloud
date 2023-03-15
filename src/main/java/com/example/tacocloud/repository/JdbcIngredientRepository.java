package com.example.tacocloud.repository;

import com.example.tacocloud.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcIngredientRepository {
//    private JdbcTemplate jdbcTemplate;
//    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//    /**
//     * @return
//     */
//    @Override
//    public List<Ingredient> findAll() {
//        return jdbcTemplate.query(
//                "select id, name, type from Ingredient",
//                this::mapRowToIngredient);
//    }
//
//    /**
//     * @param id
//     * @return
//     */
//
//    @Override
//    public Ingredient findById(String id) {
//        return jdbcTemplate.queryForObject(
//                "select id, name, type from Ingredient where id=?",
//                Ingredient.class,
//                id);
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
//        return new Ingredient(
//                row.getString("id"),
//                row.getString("name"),
//                Ingredient.Type.valueOf(row.getString("type")));
//    }
//
//    /**
//     * @param ingredient
//     * @return
//     */
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update(
//                "insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().toString());
//        return ingredient;
//    }
}
