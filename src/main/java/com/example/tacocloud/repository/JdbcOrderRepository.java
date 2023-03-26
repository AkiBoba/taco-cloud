package com.example.tacocloud.repository;

import com.example.tacocloud.domain.Ingredient;
import com.example.tacocloud.domain.Taco;
import com.example.tacocloud.domain.TacoOrder;
import com.example.tacocloud.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private static final String insert_into_Taco_Order = "insert into taco_Order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) values (?,?,?,?,?,?,?,?,?)";
    private static final String insert_into_Taco = "insert into taco (name, created_at, taco_order, taco_order_key) values (?, ?, ?, ?)";
    private static final String insert_into_Ingredient_Ref = "insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)";
    private static final String insert_into_user_order = "insert into user_order (user_id, order_id) values (?, ?)";
    private JdbcTemplate template;

    public JdbcOrderRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void saveUser(User user, Long orderId) {
        template.update(insert_into_user_order, user.getId(), orderId);
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        Long orderId;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement st = conn.prepareStatement(insert_into_Taco_Order, new String[]{"id"});
                    st.setString(1,order.getDeliveryName());
                    st.setString(2,order.getDeliveryStreet());
                    st.setString(3,order.getDeliveryCity());
                    st.setString(4,order.getDeliveryState());
                    st.setString(5,order.getDeliveryZip());
                    st.setString(6,order.getCcNumber());
                    st.setString(7,order.getCcExpiration());
                    st.setString(8,order.getCcCVV());
                    st.setTimestamp(9, new Timestamp(new Date().getTime()));
            return st;
        }, keyHolder);
        orderId = Long.valueOf(Objects.requireNonNull(keyHolder.getKeys()).get("id").toString());
        order.setId(orderId);
        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }

        return order;
    }

    private long saveTaco(Long orderId, int orderKey, Taco taco) {
        Long tacoId;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement st = conn.prepareStatement(insert_into_Taco, new String[]{"id"});
            st.setString(1, taco.getName());
            st.setTimestamp(2, new Timestamp(new Date().getTime()));
            st.setLong(3, orderId);
            st.setLong(4, orderKey);
            return st;
        }, keyHolder);
        tacoId = Long.valueOf(Objects.requireNonNull(keyHolder.getKeys()).get("id").toString());
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int key = 0;
        for (Ingredient ingredient : ingredients) {
            template.update(insert_into_Ingredient_Ref,
                ingredient.getId(), tacoId, key++);
        }
    }
}