package com.example.demo.service;

import com.example.demo.dto.ProductListResponseDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.demo.util.CustomPageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductListServiceImpl implements ProductListService{

    private final ProductListRepository productListRepository;

    @Autowired
    public ProductListServiceImpl(ProductListRepository productListRepository) {
        this.productListRepository = productListRepository;
    }

    @Override
    public List<ProductListResponseDTO> getProductsByCriteria(String mainName, String subName, int minPrice, int maxPrice, Pageable pageable) {

        Specification<Product> specification = Specification.where(null);

        if (mainName != null) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("subCategory").get("mainCategory").get("mainName"), mainName));
        }

        if (subName != null) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("subCategory").get("subName"), subName));
        }

        if (minPrice >= 0) {
            specification = specification.and((root, query, builder) ->
                    builder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice >= 0) {
            specification = specification.and((root, query, builder) ->
                    builder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }


        Page<Product> productPage = productListRepository.findAll(specification, pageable);

        List<ProductListResponseDTO> dtoList = productPage.getContent().stream()
                .map(product -> {
                    ProductListResponseDTO dto = new ProductListResponseDTO();
                    dto.setId(product.getId());
                    dto.setName(product.getName());
                    dto.setPrice(product.getPrice());
                    dto.setPicture(product.getPicture());
                    dto.setDisc(product.getDisc());
//                    dto.setSubName(product.getSubCategory().getSubName());
//                    dto.setMainName(product.getSubCategory().getMainCategory().getMainName());
                    return dto;
                })
                .collect(Collectors.toList());

        CustomPageImpl<ProductListResponseDTO> page= new CustomPageImpl<>(dtoList, pageable, productPage.getTotalElements());
        return page.getContent();

    }

}
