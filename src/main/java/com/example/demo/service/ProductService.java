package com.example.demo.service;

import com.example.demo.dto.GetProductResponseDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final OrderProductRepository orderProductRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final MyLikeRepository myLikeRepository;

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
    public GetProductResponseDTO getShowProduct(Long id, Long memberId){
        Member member1 = memberRepository.findById(memberId).orElseThrow();
        Product product = productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 상품이 없습니다. id="+id));
        boolean isLiked1 = myLikeRepository.existsByMemberAndProduct(member1, product);

        return new GetProductResponseDTO(product, isLiked1);
    }

    public void createOrderProductsFromProductIds(String productIdsString, Long memberId , int quantity) {
        // 입력 문자열을 제품 ID들로 나누기 위해 쉼표로 구분된 문자열을 분할합니다.
        String[] productIdArray = productIdsString.split(",");
        List<Long> productIdList = new ArrayList<>();
        for (String productId : productIdArray) {
            productIdList.add(Long.parseLong(productId.trim())); // Parse as Long and trim spaces
            // Integer.parseInt(productId)
        }
        // Retrieve products corresponding to the provided IDs and store them in the 'products' list.
        List<Product> products = productRepository.findByIdIn(productIdList);
        // 사용자 이름을 기반으로 회원 ID를 검색합니다.
        // 주의: 'findByUsername'의 결과를 'member' 변수에 할당해야 합니다.
        Member user = memberRepository.findById(memberId).orElseThrow();
        List<OrderProduct> orderProducts = new ArrayList<>();
        Orders order = new Orders();
        // 각 제품에 대해 OrderProduct 인스턴스를 생성합니다.
        order.setMember(user);
        for (Product product : products) {
            // 새로운 OrderProduct를 생성하고 해당 속성을 설정합니다.
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
//            orderProduct.getProduct().setName(product.getName());
            // Calculate orderPrice, handling case where price or disc is 0
            double price = product.getPrice();
            double disc = 1 - product.getDisc();
            double orderPrice = (price == 0 || disc == 0) ? price : price * disc;
            orderProduct.setOrderPrice((int) orderPrice);
//            orderProduct.setOrderPrice((int) (product.getPrice() * product.getDisc()));

            // 제품 ID와 회원 ID 조합에 기반하여 카트 카운트를 검색합니다.
            // 매개변수 위치 주의
            if(quantity == 0) {
                Cart cart = cartRepository.findByMemberIdAndProductId(user.getId(), product.getId());
                orderProduct.setCount(cart.getCount()); // Set cart count for the OrderProduct
            } else {
                orderProduct.setCount(quantity);
            }
            orderProduct.setOrderStatus(OrderStatus.ORDER);
            orderProduct.setOrders(order);
            // OrderProduct를 목록에 추가합니다.
            orderProducts.add(orderProduct);
        }

        order.setOrderProducts(orderProducts); // Orders 엔티티에 OrderProduct들을 설정
        orderRepository.save(order);
        // 새롭게 생성된 OrderProduct 엔터티들을 저장합니다.
        orderProductRepository.saveAll(orderProducts);
    }

}
