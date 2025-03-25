package com.ecommerce.product.domain.event;

import java.util.UUID;

public record ProductCreatedEvent(
        UUID productId,
        String name,
        double price,
        boolean active
) {}
