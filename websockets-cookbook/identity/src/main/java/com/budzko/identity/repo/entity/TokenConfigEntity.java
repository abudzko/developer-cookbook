package com.budzko.identity.repo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "token_config")
public class TokenConfigEntity {
    @Id
    private String login;
    @Column(name = "token_private_key")
    private byte[] tokenPrivateKey;
    @Column(name = "token_public_key")
    private byte[] tokenPublicKey;
    @Column(name = "access_token_life_time_sec")
    private long accessTokenLifeTimeSec;
    @Column(name = "refresh_token_life_time_sec")
    private long refreshTokenLifeTimeSec;
}
