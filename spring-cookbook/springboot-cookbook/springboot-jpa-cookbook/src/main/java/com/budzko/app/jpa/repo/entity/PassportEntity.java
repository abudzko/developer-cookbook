package com.budzko.app.jpa.repo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity(name = "passport")
public class PassportEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "number")
    private String number;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
