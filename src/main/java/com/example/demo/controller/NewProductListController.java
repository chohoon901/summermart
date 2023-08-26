package com.example.demo.controller;

import com.example.demo.dto.ProductListRequestDTO;
import com.example.demo.dto.ProductListResponseDTO;
import com.example.demo.dto.SortingCriteriaDTO;
import com.example.demo.service.NewProductListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewProductListController {

    private final NewProductListService productListService;

//    @GetMapping("/product_list_main")
//    public List<ProductListResponseDTO> getProductsByMainCategory(@RequestBody ProductListRequestDTO requestDTO)
//    {
//        List<ProductListResponseDTO> responseDTO =
//                productListService.getProductsByMainCategory(requestDTO.getName());
//
//        return responseDTO;
//    }
//
//    @GetMapping("/product_list_sub")
//    public List<ProductListResponseDTO> getProductsBySubCategory(@RequestBody ProductListRequestDTO requestDTO)
//    {
//        List<ProductListResponseDTO> responseDTO =
//                productListService.getProductsBySubCategory(requestDTO.getName());
//
//        return responseDTO;
//    }

    /* sort and pagination*/

    @PostMapping("/best_mainCategory")
    public List<ProductListResponseDTO> getProductsByMainCategorySorted(
            @RequestBody SortingCriteriaDTO sortingCriteria) {
        return productListService.getProductsByMainCategorySorted(sortingCriteria);
    }

    @PostMapping("/best_subCategory")
    public List<ProductListResponseDTO> getProductsBySubCategorySorted(
            @RequestBody SortingCriteriaDTO sortingCriteria) {
        return productListService.getProductsBySubCategorySorted(sortingCriteria);
    }
}
