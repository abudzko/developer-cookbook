package com.budzko.cookbook.spring.springboot.crud.app.dao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Developer {
    @Id
    private long id;
    private String name;
    private String email;
}