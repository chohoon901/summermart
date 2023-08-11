package com.example.demo.recommend;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "prod_tbl2")
public class Recommend {
    @Id
    private int ID;

    private String SUBCAT;

    private String PROD;

    private int BASEP;

    private int DISC;

    private int PRICV;

    private int REW;

    private float RATE;

    private int REC;

    private String IMAGE;

}
