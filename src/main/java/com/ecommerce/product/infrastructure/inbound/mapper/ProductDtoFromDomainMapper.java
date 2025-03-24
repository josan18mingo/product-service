package com.ecommerce.product.infrastructure.inbound.mapper;


import com.ecommerce.product.domain.entity.Product;
import org.openapitools.model.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoFromDomainMapper {

    public ProductDTO map(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setName(product.getName());
        return productDTO;
    }
}
