package com.budzko.identity.service;

import com.budzko.identity.mapper.UserMapper;
import com.budzko.identity.model.http.UserRegistrationRequest;
import com.budzko.identity.repo.TokenConfigRepo;
import com.budzko.identity.repo.UserRepo;
import com.budzko.identity.repo.entity.TokenConfigEntity;
import com.budzko.identity.repo.entity.UserEntity;
import com.budzko.identity.service.token.RsaKeyPairUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final TokenConfigRepo tokenConfigRepo;
    private final UserMapper userMapper;

    private static TokenConfigEntity createTokenConfigEntity(UserRegistrationRequest userRegistrationRequest) {
        TokenConfigEntity tokenConfigEntity = new TokenConfigEntity();
        tokenConfigEntity.setLogin(userRegistrationRequest.getUserName());
        tokenConfigEntity.setAccessTokenLifeTimeSec(TimeUnit.MINUTES.toSeconds(15));
        tokenConfigEntity.setRefreshTokenLifeTimeSec(TimeUnit.HOURS.toSeconds(1));

        KeyPair keyPair = RsaKeyPairUtils.generateKeyPair();
        tokenConfigEntity.setTokenPrivateKey(keyPair.getPrivate().getEncoded());
        tokenConfigEntity.setTokenPublicKey(keyPair.getPublic().getEncoded());
        return tokenConfigEntity;
    }

    @Transactional
    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        TokenConfigEntity tokenConfigEntity = createTokenConfigEntity(userRegistrationRequest);
        tokenConfigRepo.save(tokenConfigEntity);

        UserEntity userEntity = createUserEntity(userRegistrationRequest);
        userRepo.save(userEntity);
        log.info("User was registered: {}", userRegistrationRequest.getUserName());
    }

    public byte[] getPublicKey(String login) {
        return tokenConfigRepo.getTokenPublicKeyById(login);
    }

    private UserEntity createUserEntity(UserRegistrationRequest userRegistrationRequest) {
        UserEntity userEntity = userMapper.convert(userRegistrationRequest);
        userEntity.setLogin(userRegistrationRequest.getUserName());
        userEntity.setPasswordHash(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        return userEntity;
    }
}
