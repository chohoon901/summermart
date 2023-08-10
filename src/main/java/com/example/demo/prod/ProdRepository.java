package com.example.demo.prod;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdRepository extends JpaRepository<Prod, String> {

    @Query(value = "SELECT * FROM prod_tbl2 WHERE PROD LIKE %:word% LIMIT 5", nativeQuery = true)
    List<Prod> findProd(@Param(value="word") String word);

}
