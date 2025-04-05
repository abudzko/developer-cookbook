package com.budzko.identity.controller;

import com.budzko.identity.model.http.TokenResponse;
import com.budzko.identity.model.http.UserRegistrationRequest;
import com.budzko.identity.repo.TokenConfigRepo;
import com.budzko.identity.repo.entity.TokenConfigEntity;
import com.budzko.identity.service.token.RsaKeyPairUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//@TestPropertySource(
//        properties = {
//                "management.security.enabled=false",
//                "security.basic.enabled=false"
//        }
//)
class IdentityControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenConfigRepo tokenConfigRepo;

    private static String buildAuthorizationHeader(String login, String password) {
        String loginPasswordPair = login + ":" + password;
        return "Basic " + new String(
                Base64.getEncoder().encode(loginPasswordPair.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8
        );
    }

//    @Test
    void test() throws Exception {
        String login = "login";
        String password = "password";
        UserRegistrationRequest request = UserRegistrationRequest.builder().userName(login).password(password).build();
        String requestBody = objectMapper.writeValueAsString(request);
        MvcResult mvcResult = mockMvc.perform(
                        post("/register")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        mvcResult = mockMvc.perform(
                        post("/accessToken")
                                .header(
                                        HttpHeaders.AUTHORIZATION,
                                        buildAuthorizationHeader(login, password)
                                ))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        TokenResponse tokenResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );
        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();

        TokenConfigEntity securityConfigEntity = tokenConfigRepo.findById(login).orElseThrow();
        Jws<Claims> accessTokenClaims = Jwts.parserBuilder()
                .setSigningKey(RsaKeyPairUtils.toPublicKey(securityConfigEntity.getTokenPublicKey()))
                .build()
                .parseClaimsJws(accessToken);
        Jws<Claims> refreshTokenClaims = Jwts.parserBuilder()
                .setSigningKey(RsaKeyPairUtils.toPublicKey(securityConfigEntity.getTokenPublicKey()))
                .build()
                .parseClaimsJws(refreshToken);

        System.out.println(accessTokenClaims);
        System.out.println(refreshTokenClaims);
//        Assertions.

    }
}
