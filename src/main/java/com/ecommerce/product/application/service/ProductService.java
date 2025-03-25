package com.ecommerce.product.application.service;

import com.ecommerce.product.application.port.ProductServicePort;
import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.application.port.ProductRepositoryPort;
import com.ecommerce.product.domain.event.ProductCreatedEvent;
import com.ecommerce.product.infrastructure.outbound.kafka.KafkaProductEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort repository;
    private final KafkaProductEventPublisher kafkaProductEventPublisher;


    @Override
    public Product createProduct(String name, double price) {
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .price(price)
                .name(name)
                .active(true)
                .build();
        Product productSaved = repository.save(product);
        ProductCreatedEvent event = new ProductCreatedEvent(
                productSaved.getId(),
                productSaved.getName(),
                productSaved.getPrice(),
                productSaved.isActive()
        );
        kafkaProductEventPublisher.publish(event);
        return productSaved;
    }

    @Override
    public List<Product> getActiveProducts() {
        return repository.findAllActive();
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return repository.findById(id);
    }

}
