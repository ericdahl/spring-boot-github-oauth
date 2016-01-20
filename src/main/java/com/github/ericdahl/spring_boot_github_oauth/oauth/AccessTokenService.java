package com.github.ericdahl.spring_boot_github_oauth.oauth;

import com.github.ericdahl.spring_boot_github_oauth.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Service
public class AccessTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenService.class);

    private final ParameterizedTypeReference<Map<String, String>> TYPE_REF_MAP_STRING_STRING = new ParameterizedTypeReference<Map<String, String>>() { };

    @Value("${api.client_id}")
    private String clientId;

    @Value("${api.client_secret}")
    private String clientSecret;

    private RestTemplate restTemplate = new RestTemplate();

    public String getAccessToken(final String code) throws URISyntaxException {

        final AccessTokenRequest accessTokenRequest = new AccessTokenRequest(clientId, clientSecret, code);
        final RequestEntity<AccessTokenRequest> requestEntity = new RequestEntity<>(accessTokenRequest, HttpMethod.POST, new URI("https://github.com/login/oauth/access_token"));
        final ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(requestEntity, TYPE_REF_MAP_STRING_STRING);

        if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
            throw new ApiException(responseEntity);
        }

        final Map<String, String> responseBody = responseEntity.getBody();

        LOGGER.info("access_token response payload {}", responseBody);

        return responseBody.get("access_token");
    }
}
