package com.example.demo;

import com.example.demo.entity.Recommend;
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
		List<Recommend> result = repository.findProd("메밀");
		System.out.println(result);
	}

}
