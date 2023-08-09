package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy = "subCategory")
    private List<Product> product = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainCategory_id")
    private MainCategory mainCategory;

    private String subName;

    public static SubCategory createSubCategory(MainCategory mainCategory, String subName) {
        SubCategory subCategory = new SubCategory();
        subCategory.setMainCategory(mainCategory);
        subCategory.setSubName(subName);

        return subCategory;
    }
}
