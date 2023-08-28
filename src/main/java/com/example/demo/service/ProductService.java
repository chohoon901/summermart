package com.example.demo.service;

import com.example.demo.dto.GetProductResponseDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.MainCategory;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MyLikeRepository;
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
    private final MyLikeRepository myLikeRepository;
    private final MemberRepository memberRepository;


    // TODO : getProduct
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> p != null ? new ProductDTO(p) : null)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public GetProductResponseDTO getShowProduct(Long id, Member member){
        Member member1 = memberRepository.findByUsername(member.getUsername());
        Product product = productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id="+id));
        boolean isLiked1 = myLikeRepository.existsByMemberAndProduct(member1, product);

        return new GetProductResponseDTO(product, isLiked1);
    }

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
}
