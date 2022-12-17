package com.budzko.app.jpa.repo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "car")
public class CarEntity {
    @Id
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "user_id")
    private String userId;
}
