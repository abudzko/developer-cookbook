package com.budzko.identity.service.token;

import com.budzko.identity.repo.RefreshTokenRepo;
import com.budzko.identity.repo.entity.RefreshTokenEntity;
import com.budzko.identity.service.token.factory.TokenConfig;
import com.budzko.identity.service.token.factory.TokenFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepo refreshTokenRepo;
    private final TokenFactory tokenFactory;

    private static boolean expired(RefreshTokenEntity refreshTokenEntity) {
        return refreshTokenEntity.getExpireAt() == null
                || LocalDateTime.now().isAfter(refreshTokenEntity.getExpireAt());
    }

    @Transactional
    public String getRefreshToken(TokenConfig tokenConfig) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepo.findById(tokenConfig.getLogin())
                .orElse(new RefreshTokenEntity());
        String refreshToken;
        if (StringUtils.isEmpty(refreshTokenEntity.getRefreshToken()) || expired(refreshTokenEntity)) {
            refreshToken = createRefreshToken(tokenConfig);
        } else {
            refreshToken = refreshTokenEntity.getRefreshToken();
        }
        return refreshToken;
    }

    private String createRefreshToken(TokenConfig tokenConfig) {
        String refreshToken = tokenFactory.buildRefreshToken(tokenConfig);
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setLogin(tokenConfig.getLogin());
        refreshTokenEntity.setRefreshToken(refreshToken);
        refreshTokenEntity.setExpireAt(tokenConfig.getRefreshTokenExpireAt());
        refreshTokenRepo.save(refreshTokenEntity);
        return refreshToken;
    }
}
