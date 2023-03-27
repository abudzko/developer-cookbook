package com.budzko.token.storage.redis;

import com.budzko.token.utils.RsaKeyPairUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.util.Optional;

@Service
public class RedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    public Optional<PublicKey> getPublicKey(String login) {
        String data = jedisCluster.get(login);
        if (StringUtils.hasLength(data)) {
            return Optional.of(RsaKeyPairUtils.toPublicKey(data.getBytes(StandardCharsets.UTF_8)));
        }
        return Optional.empty();
    }

    public void savePublicKey(String login, PublicKey publicKey) {
        jedisCluster.set(login.getBytes(StandardCharsets.UTF_8), publicKey.getEncoded());
    }
}
