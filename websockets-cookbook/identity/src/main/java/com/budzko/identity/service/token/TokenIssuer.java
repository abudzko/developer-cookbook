package com.budzko.identity.service.token;

import com.budzko.identity.model.http.TokenResponse;
import com.budzko.identity.repo.TokenConfigRepo;
import com.budzko.identity.repo.entity.TokenConfigEntity;
import com.budzko.identity.service.token.factory.TokenConfig;
import com.budzko.identity.service.token.factory.TokenFactory;
import com.budzko.identity.utils.DateUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenIssuer {
    private final TokenFactory tokenFactory;
    private final RefreshTokenService refreshTokenService;
    private final TokenConfigRepo tokenConfigRepo;

    private static TokenConfig createTokenConfig(TokenConfigEntity tokenConfigEntity) {
        LocalDateTime now = DateUtils.now();
        return TokenConfig.builder()
                .login(tokenConfigEntity.getLogin())
                .now(now)
                .algorithm(SignatureAlgorithm.PS512)
                .accessTokenExpireAt(now.plusSeconds(tokenConfigEntity.getAccessTokenLifeTimeSec()))
                .refreshTokenExpireAt(now.plusSeconds(tokenConfigEntity.getRefreshTokenLifeTimeSec()))
                .privateKey(RsaKeyPairUtils.toPrivateKey(tokenConfigEntity.getTokenPrivateKey()))
                .build();
    }

    public TokenResponse issueToken(String login) {
        TokenConfigEntity tokenConfigEntity = tokenConfigRepo.findById(login).orElseThrow();
        TokenConfig tokenConfig = createTokenConfig(tokenConfigEntity);
        return TokenResponse.builder()
                .accessToken(tokenFactory.buildAccessToken(tokenConfig))
                .refreshToken(refreshTokenService.getRefreshToken(tokenConfig))
                .build();
    }
}
