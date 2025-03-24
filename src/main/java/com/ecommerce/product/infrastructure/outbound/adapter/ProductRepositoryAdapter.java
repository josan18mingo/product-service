package com.ecommerce.product.infrastructure.outbound.adapter;

import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.application.port.ProductRepositoryPort;
import com.ecommerce.product.infrastructure.outbound.entity.ProductEntity;
import com.ecommerce.product.infrastructure.outbound.mapper.ProductEntityFromDomainMapper;
import com.ecommerce.product.infrastructure.outbound.mapper.ProductEntityToDomainMapper;
import com.ecommerce.product.infrastructure.outbound.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository jpaRepository;
    private final ProductEntityFromDomainMapper productEntityFromDomainMapper;
    private final ProductEntityToDomainMapper productEntityToDomainMapper;

    @Override
    public Optional<Product> findById(UUID id) {
        Optional<ProductEntity> productEntity = jpaRepository.findById(id);
        return productEntity.map(productEntityToDomainMapper::map);
    }

    @Override
    public List<Product> findAllActive() {
        List<ProductEntity> productList = jpaRepository.findByActiveTrue();
        return productList.stream().map(productEntityToDomainMapper::map).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productEntityFromDomainMapper.map(product);
        ProductEntity returnedProduct = jpaRepository.save(productEntity);
        return productEntityToDomainMapper.map(returnedProduct);
    }
}
