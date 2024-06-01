package com.example.shop.controller;

import com.example.shop.model.Item;
import com.example.shop.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ItemRepository itemRepository;
    public AdminController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @GetMapping
    private String adminPage() {
    return "addItem";
    }
    @PostMapping
    private String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }
}
