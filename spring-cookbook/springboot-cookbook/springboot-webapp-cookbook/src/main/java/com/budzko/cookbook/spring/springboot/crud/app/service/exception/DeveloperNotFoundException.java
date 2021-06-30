package com.budzko.cookbook.spring.springboot.crud.app.service.exception;


public class DeveloperNotFoundException extends RuntimeException {
    public DeveloperNotFoundException(String msg) {
        super(msg);
    }
}
