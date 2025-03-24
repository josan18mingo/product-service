package com.ecommerce.product.infrastructure.outbound.mapper;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.infrastructure.outbound.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityFromDomainMapper {
    public ProductEntity map(Product product){

        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .active(product.isActive())
                .build();
    }
}
