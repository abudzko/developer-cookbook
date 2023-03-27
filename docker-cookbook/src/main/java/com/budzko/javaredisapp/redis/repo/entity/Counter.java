package com.budzko.javaredisapp.redis.repo.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Counter")
@Data
public class Counter {
    private String id;
    private Integer value;
}
