package com.budzko.identity.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "refresh_token")
public class RefreshTokenEntity {
    @Id
    private String login;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "expire_at")
    private LocalDateTime expireAt;
}
