package com.ecommerce.product.application.port;

import com.ecommerce.product.domain.event.ProductCreatedEvent;

public interface EventPublisher {
    void publish(ProductCreatedEvent event);
}
