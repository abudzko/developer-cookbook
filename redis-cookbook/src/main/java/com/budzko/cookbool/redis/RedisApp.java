package com.budzko.cookbool.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisApp {

    protected static final String LOCALHOST = "localhost";

    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        Map.of(
                7000, LOCALHOST,
                7001, LOCALHOST,
                7002, LOCALHOST,
                7003, LOCALHOST,
                7004, LOCALHOST,
                7005, LOCALHOST,
                7006, LOCALHOST
        ).forEach((port, host) -> jedisClusterNodes.add(new HostAndPort(host, port)));
        try (JedisCluster jedis = new JedisCluster(jedisClusterNodes)) {
            String key = "planets";
//            jedis.set(key, "Mars");
            System.out.println("Value:" + jedis.get(key));
        }
    }
}
