package com.example.demo.service;

import com.example.demo.util.PageInfo;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.MainCategory;
import com.example.demo.entity.Product;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ProductDTO> getProductsPageSorted(int page, int pageSize, String sort) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by(sort));
        Page<Product> productPage = productRepository.findAll(pageRequest);

        return productPage.getContent().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public List<PageInfo> getPaginationInfo(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        int totalPages = productPage.getTotalPages();
        List<PageInfo> pages = new ArrayList<>();

        for (int i = 0; i < totalPages; i++) {
            pages.add(new PageInfo(i == currentPage, i));
        }

        return pages;
    }


    // 모든 상품 정보 조회
    public List<Product> getAllProducts_old() {
        return productRepository.findAll();
    }

}
