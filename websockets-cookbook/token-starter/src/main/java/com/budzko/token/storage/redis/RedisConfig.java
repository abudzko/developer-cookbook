package com.budzko.token.storage.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
public class RedisConfig {

    @Value("#{${redis.cluster.host-port-pairs : { 6379: 'localhost'}}}")
    private Map<Integer, String> redisClusterPortHostPairs;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
//        Map.of(
//                7000, LOCALHOST,
//                7001, LOCALHOST,
//                7002, LOCALHOST,
//                7003, LOCALHOST,
//                7004, LOCALHOST,
//                7005, LOCALHOST,
//                7006, LOCALHOST
//        );
//        redisClusterPortHostPairs.forEach((port, host) -> jedisClusterNodes.add(new HostAndPort(host, port)));
//        return new JedisCluster(jedisClusterNodes);
        return null;

    }
}
