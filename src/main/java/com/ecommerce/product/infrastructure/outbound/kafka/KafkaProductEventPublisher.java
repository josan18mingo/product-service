package com.ecommerce.product.infrastructure.outbound.kafka;

import com.ecommerce.product.application.port.EventPublisher;
import com.ecommerce.product.domain.event.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProductEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "product-events";

    @Override
    public void publish(ProductCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event.productId().toString(), event);
    }
}