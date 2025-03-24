package com.ecommerce.product.infrastructure.outbound.mapper;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.infrastructure.outbound.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToDomainMapper {
    public Product map(ProductEntity productEntity){
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .active(productEntity.isActive())
                .build();
    }
}
