package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity(name = "prod_tbl2")
@Data
public class Recommend {
    @Id
    private int id;

    private String subcat;

    private String prod;

    private int basep;

    private int disc;

    private int pricv;

    private int rew;

    private float rate;

    private int rec;

    private String image;

}
