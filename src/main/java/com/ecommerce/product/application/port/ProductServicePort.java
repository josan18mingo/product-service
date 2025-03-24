package com.ecommerce.product.application.port;

import com.ecommerce.product.domain.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductServicePort {

    Product createProduct(String name, double price);
    List<Product> getActiveProducts();
    Optional<Product> getProductById(UUID id);
}
