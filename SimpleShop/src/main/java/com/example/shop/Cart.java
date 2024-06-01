package com.example.shop;

import com.example.shop.model.Item;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        boolean notFound = true;
        for (CartItem ci: cartItems) {
            if (ci.getItem().getId().equals(item.getId())) {
                notFound = false;
                ci.increaseCounter();
                recalculatePriceAndCounter();
                break;
            }
        }

        if (notFound) {
            cartItems.add(new CartItem(item));
            recalculatePriceAndCounter();
        }

    }
    public void removeItem(Item item) {
        for (CartItem ci: cartItems) {
            if (ci.getItem().getId().equals(item.getId())) {
            ci.decreaseCounter();
            if (ci.hasZeroItems()) {
                cartItems.remove(ci);
            }
            recalculatePriceAndCounter();
            break;
            }
        }
    }
    private void recalculatePriceAndCounter() {
        int tempCounter =0;
        BigDecimal tempPrice = BigDecimal.ZERO;

        for (CartItem ci: cartItems) {
            tempCounter += ci.getCounter();
            tempPrice = tempPrice.add(ci.getPrice());
        }
        this.counter = tempCounter;
        this.sum = tempPrice;
    }

    public void removeAllItems(Item item) {
        for (CartItem ci: cartItems) {
            cartItems.remove(ci);
            break;
        }
        recalculatePriceAndCounter();
    }
    public void clearCart() {
        cartItems.clear();
        counter = 0;
        sum = BigDecimal.ZERO;
    }

}

