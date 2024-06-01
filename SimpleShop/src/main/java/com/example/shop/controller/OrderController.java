package com.example.shop.controller;

import com.example.shop.OrderSummary;
import com.example.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;

    @Autowired
    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }

    @GetMapping("/summary")
    public String showSummary() {
        return "summary";

    }

    @GetMapping("/increase/{itemId}")
    public String increaseItem(@PathVariable("itemId") Long itemId) {
        cartService.addItemToCart(itemId);
        return "cartView";
    }
    @GetMapping("/decrease/{itemId}")
    public String decreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.decreaseItem(itemId);
        return "cartView";
    }
    @GetMapping("/remove/{itemId}")
    public String removeAllItemsFromCart(@PathVariable("itemId") Long itemId) {
        cartService.removeAllItems(itemId);
        return "cartView";
    }
    @PostMapping("/saveorder")
    public String saveOrder(OrderSummary orderSummary) {
        cartService.saveOrderAndClearCart(orderSummary);
        return "redirect:/";
    }
}
