package com.ecommerce.product.infrastructure.inbound.controller;

import com.ecommerce.product.application.port.ProductServicePort;
import com.ecommerce.product.domain.entity.Product;
import com.ecommerce.product.infrastructure.inbound.mapper.ProductDtoFromDomainMapper;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProductsApi;
import org.openapitools.model.CreateProductDTO;
import org.openapitools.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductsApi {

    private final ProductServicePort productServicePort;
    private final ProductDtoFromDomainMapper productDtoFromDomainMapper;


    @Override
    public ResponseEntity<Void> createProduct(CreateProductDTO createProductDTO) {
        Product product = productServicePort.createProduct(createProductDTO.getName(), createProductDTO.getPrice());
        if (Objects.isNull(product)) {
            return new ResponseEntity<>(HttpStatus.CREATED); //cambiar a error
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        List<Product> productList = productServicePort.getActiveProducts();
        List<ProductDTO> productDTOList = productList.stream().map(productDtoFromDomainMapper::map).toList();
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(UUID id) {
        Optional<Product> product = productServicePort.getProductById(id);
        return product.map(value -> new ResponseEntity<>(productDtoFromDomainMapper.map(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
