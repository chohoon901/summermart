package com.example.demo.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class ProdController {

    @Autowired
    private ProdRepository prodRepository;

    @RequestMapping("/search")

    @GetMapping("/get_similar_products")
    public ResponseEntity<List<Prod>> getSimilarProducts(@RequestParam String input_word) {
        List<Prod> similarProducts = findSimilarProducts(input_word);
        return ResponseEntity.ok(similarProducts);
    }

    private List<Prod> findSimilarProducts(String inputWord) {
        List<Prod> similarProducts = new ArrayList<>();

        // Find similar words using the loaded model
        List<String> similarWords = findSimilarWords(inputWord);

        for (String word : similarWords) {
            List<Prod> prodList = prodRepository.findProd("%" + word + "%");
            similarProducts.addAll(prodList);
        }

        // Randomly limit results to 5
        Collections.shuffle(similarProducts);
        int limit = Math.min(similarProducts.size(), 5);
        return similarProducts.subList(0, limit);
    }

    private List<String> findSimilarWords(String inputWord) {
        // Use loaded_model.wv.most_similar to find similar words using the loaded model
        // Implement this part based on your loaded model
        // For now, let's assume similar words are hardcoded
        List<String> similarWords = Arrays.asList();
        return similarWords;
    }
}