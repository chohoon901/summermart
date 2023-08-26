package com.example.demo.service;

import com.example.demo.dto.ProductListResponseDTO;
import com.example.demo.dto.SortingCriteriaDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductListRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewProductListService {

    private final ProductListRepository productListRepository;

//    // 대분류 찾기
//    public List<ProductListResponseDTO> getProductsByMainCategory(String mainCategoryName) {
//        List<Product> product = productListRepository.findBySubCategory_MainCategory_MainName(mainCategoryName);
//
//        // Transform Product entities into ProductListResponseDTO objects
//        List<ProductListResponseDTO> responseDTO = product
//                .stream()
//                .map(this::mainProductToResponseDTO)
//                .collect(Collectors.toList());
//
//        return responseDTO;
//    }
//
//    private ProductListResponseDTO mainProductToResponseDTO(Product product) {
//        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
//        responseDTO.setId(product.getId());
//        responseDTO.setPicture(product.getPicture());
//        responseDTO.setName(product.getName());
//        responseDTO.setPrice(product.getPrice());
//        responseDTO.setDisc(product.getDisc());
//        return responseDTO;
//    }
//
//    // 소분류 찾기
//    public List<ProductListResponseDTO> getProductsBySubCategory(String subCategoryName) {
//        List<Product> product = productListRepository.
//                findBySubCategory_SubName(subCategoryName);
//
//        // Transform Product entities into ProductListResponseDTO objects
//        List<ProductListResponseDTO> responseDTO = product
//                .stream()
//                .map(this::subProductToResponseDTO)
//                .collect(Collectors.toList());
//
//        return responseDTO;
//    }
//
//    private ProductListResponseDTO subProductToResponseDTO(Product product) {
//        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
//        responseDTO.setId(product.getId());
//        responseDTO.setPicture(product.getPicture());
//        responseDTO.setName(product.getName());
//        responseDTO.setPrice(product.getPrice());
//        responseDTO.setDisc(product.getDisc());
//        return responseDTO;
//    }

    /*  sort 및 pagination */

    public List<ProductListResponseDTO> getProductsByMainCategorySorted(SortingCriteriaDTO sortingCriteria) {
        Sort sort = createSortFromCriteria(sortingCriteria);
        Pageable pageable = PageRequest.of(sortingCriteria.getPageNumber(), 8, sort);
        Page<Product> products = productListRepository.findBySubCategory_MainCategory_MainName(
                sortingCriteria.getCategoryName(), pageable);
        return transformToResponseDTOs(products.getContent());
    }


    public List<ProductListResponseDTO> getProductsBySubCategorySorted(SortingCriteriaDTO sortingCriteria) {
        Sort sort = createSortFromCriteria(sortingCriteria);
        Pageable pageable = PageRequest.of(sortingCriteria.getPageNumber(), 8, sort);

        Page<Product> products = productListRepository.findBySubCategory_SubName(sortingCriteria.getCategoryName(), pageable);
        return transformToResponseDTOs(products.getContent());
    }

    private Sort createSortFromCriteria(SortingCriteriaDTO sortingCriteria) {
        String sortBy = sortingCriteria.getSortBy();
        String sortDirection = sortingCriteria.getSortDirection();

        Sort.Direction direction = sortDirection.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC
                : Sort.Direction.DESC;
        return Sort.by(direction, sortBy);
    }

    private List<ProductListResponseDTO> transformToResponseDTOs(List<Product> products) {
        return products.stream()
                .map(this::mapProductToResponseDTO)
                .collect(Collectors.toList());
    }

    private ProductListResponseDTO mapProductToResponseDTO(Product product) {
        ProductListResponseDTO responseDTO = new ProductListResponseDTO();
        responseDTO.setId(product.getId());
        responseDTO.setPicture(product.getPicture());
        responseDTO.setName(product.getName());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setDisc(product.getDisc());
        return responseDTO;
    }


}