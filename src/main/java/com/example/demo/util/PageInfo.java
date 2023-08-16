package com.example.demo.util;

import lombok.Data;

@Data
public class PageInfo {
    private boolean current;
    private int number;

    public PageInfo(boolean current, int number) {
        this.current = current;
        this.number = number;
    }
}