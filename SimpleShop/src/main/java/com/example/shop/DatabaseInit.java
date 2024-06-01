package com.example.shop;

import com.example.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseInit implements CommandLineRunner {
    private final ItemRepository itemRepository;
    @Autowired
    public DatabaseInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
        ));
    }
}
