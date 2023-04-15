package com.example.tacocloud.web.repository;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCTacoPepository implements TacoPepository {
    private static final String find_all_tacos = "select * from taco order by created_at desc limit 12";
    private static final String find_by_id = "SELECT i.*, ir.taco FROM ingredient as i join ingredient_ref as ir on ir.ingredient = i.id where ir.taco = ?";

    private final JdbcTemplate template;

    public JDBCTacoPepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Taco> findAll() {
        return template.query(find_all_tacos, (rs, row) -> new Taco(
                rs.getLong("id"),
                rs.getTimestamp("created_at"),
                rs.getString("name")
        ));
    }

    @Override
    public List<Ingredient> findById(Long id) {
        return template.query(find_by_id, (rs, row) -> new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type"))
        ), id);
    }
}
