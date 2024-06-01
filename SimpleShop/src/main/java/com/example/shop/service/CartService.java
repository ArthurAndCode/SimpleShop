package com.example.shop.service;

import com.example.shop.Cart;
import com.example.shop.CartItem;
import com.example.shop.OrderSummary;
import com.example.shop.model.Item;
import com.example.shop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final ItemRepository itemRepository;
    private final Cart cart;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart, JdbcTemplate jdbcTemplate) {
        this.itemRepository = itemRepository;
        this.cart = cart;
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    public void addItemToCart(Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.addItem(item);
        }
    }

    public void decreaseItem(Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.removeItem(item);
        }
    }
    public void removeAllItems(Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            Item item = oItem.get();
            cart.removeAllItems(item);
        }
    }
    @Transactional
    public void saveOrderAndClearCart(OrderSummary orderSummary) {
        String sql = "INSERT INTO ordertable (first_name, last_name, address, post_code, city, order_date, item_id, item_name, item_quantity, item_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Date orderDate = new Date();

        for (CartItem cartItem : cart.getCartItems()) {
            jdbcTemplate.update(sql,
                    orderSummary.getFirstName(),
                    orderSummary.getLastName(),
                    orderSummary.getAddress(),
                    orderSummary.getPostCode(),
                    orderSummary.getCity(),
                    orderDate,
                    cartItem.getItem().getId(),
                    cartItem.getItem().getName(),
                    cartItem.getCounter(),
                    cartItem.getPrice()
            );
        }
        cart.clearCart();
    }
}
