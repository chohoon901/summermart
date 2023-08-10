package com.example.demo;

import com.example.demo.prod.Prod;
import com.example.demo.prod.ProdRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SummermartApplicationTests {
	@Autowired
	ProdRepository repository;

	@Test
	void contextLoads() {
		List<Prod> result = repository.findProd("메밀");
		System.out.println(result);
	}

}
