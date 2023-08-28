package com.example.demo.exception;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String message) {
        super(message);
    }
}
