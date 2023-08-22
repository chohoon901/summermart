package com.example.demo.service;

import com.example.demo.dto.GetProductResponseDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.MainCategory;
import com.example.demo.entity.Product;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    // 상품 정보 생성
    public void createProduct(ProductDTO productDTO) {
        MainCategory mainCategory = new MainCategory();
        mainCategory.setMainName(productDTO.getMainName());

        SubCategory subCategory = SubCategory.createSubCategory(mainCategory, productDTO.getSubName());

        Product product = Product.createProduct(
                subCategory, productDTO.getStock(), productDTO.getName(), productDTO.getPrice(), productDTO.getPicture(),
                productDTO.getDisc()
                );
        productRepository.save(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> p != null ? new ProductDTO(p) : null)
                .filter(obj -> Objects.nonNull(obj))
                .collect(Collectors.toList());
    }

    // 단건 조회

    public GetProductResponseDTO getShowProduct(Long id){

        Product product = productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id="+id));

        return new GetProductResponseDTO(product);
    }



}
