package com.budzko.identity.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "users")
public class UserEntity {
    @Id
    private String login;
    @Column(name = "password_hash")
    private String passwordHash;
}
