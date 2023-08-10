package com.example.demo.prod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Prod, String> {

    @Query(value = "SELECT PROD FROM prod_tbl2 WHERE PROD LIKE  %:word%", nativeQuery = true)
    public List<Prod> findProd();
}
