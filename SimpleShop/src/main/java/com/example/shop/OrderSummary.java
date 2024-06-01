package com.example.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderSummary {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
}