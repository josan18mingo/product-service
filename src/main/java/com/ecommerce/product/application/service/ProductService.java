package com.ecommerce.product.application.service;

import com.ecommerce.product.application.port.ProductServicePort;
import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.application.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductRepositoryPort repository;


    @Override
    public Product createProduct(String name, double price) {
        Product product = Product.builder()
                .id(UUID.randomUUID())
                .price(price)
                .name(name)
                .active(true)
                .build();
        return repository.save(product);
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
