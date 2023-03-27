package com.budzko.token.storage.http;

import com.budzko.token.utils.RsaKeyPairUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

@Service
public class IdentityServiceHttpClient {

    @Autowired
    private HttpClient httpClient;

    public PublicKey getPublicKey(String login) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("http://localhost:8080/getPublicKey?login=" + login))//TODO PUT in request body
                .build();
        HttpResponse<String> httpResponse = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)
        );
        return RsaKeyPairUtils.toPublicKey(httpResponse.body().getBytes(StandardCharsets.UTF_8));
    }
}
