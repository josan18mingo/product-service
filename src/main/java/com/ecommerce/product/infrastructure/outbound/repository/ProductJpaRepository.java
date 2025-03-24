package com.ecommerce.product.infrastructure.outbound.repository;

import com.ecommerce.product.infrastructure.outbound.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findByActiveTrue();
}
