package com.ecommerce.product.domain.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private double price;
    private boolean active;

    public void deactivate() {
        this.active = false;
    }
}
