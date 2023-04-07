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
    private static final String find_user_orders = "SELECT uo.user_id as user_id, o.id as order_id, o.placed_at as placed_at FROM user_order as uo JOIN taco_order AS o ON o.id = uo.order_id where uo.user_id = ? ORDER by o.placed_at DESC LIMIT ?";
    private static final String delete_by_id = "delete from taco_order where id = ?";
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

    @Override
    public List<TacoOrder> findByUserOrders(User user, Integer pageSize) {
        return template.query(find_user_orders,
                (rs, num) -> new TacoOrder(rs.getLong("order_id"),
                        rs.getTimestamp("placed_at")),
                user.getId(),
                pageSize);
    }

    @Override
    public void deleteById(Long orderId) {
        template.update(delete_by_id, orderId);
    }

}