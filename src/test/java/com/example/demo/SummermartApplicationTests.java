package com.example.demo;

import com.example.demo.entity.Product;
import com.example.demo.repository.RecommendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SummermartApplicationTests {
	@Autowired
	RecommendRepository repository;

	@Test
	void contextLoads() {
		List<Product> result = repository.findProd("국수");
		System.out.println(result);
	}

}
