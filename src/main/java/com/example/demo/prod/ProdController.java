//package com.example.demo.prod;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//public class ProdController {
//
//    private final String FLASK_SERVER_URL = "http://127.0.0.1:5000"; // Flask 서버 주소
//
//    @Autowired
//    private ProdRepository prodRepository;
//
//    @PostMapping("/search")
//    public ResponseEntity<List<String>> getSimilarProducts(@RequestBody String inputWord) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Flask 서버의 API 엔드포인트에 요청을 보냄
//        ResponseEntity<List> response = restTemplate.postForEntity(
//                FLASK_SERVER_URL + "/get_similar_products",
//                "{\"input_word\": \"" + inputWord + "\"}",
//                List.class
//        );
//
//        List<String> similarProductNamesFromFlask = response.getBody();
//
//        // Spring Data JPA를 이용하여 데이터베이스에서 유사한 제품 정보를 찾음
////        List<Prod> similarProductsFromDB = prodRepository.findProd("%" + inputWord + "%");
//
//        // 결과를 합침 (Flask 결과 + 데이터베이스 결과)
////        similarProductNamesFromFlask.addAll(
////                similarProductsFromDB.stream()
////                        .map(Prod::getPROD)
////                        .collect(Collectors.toList())
////        );
//
//        return ResponseEntity.ok(similarProductNamesFromFlask);
//    }
//}