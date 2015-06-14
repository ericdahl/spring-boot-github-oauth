package com.github.ericdahl.spring_boot_github_oauth.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final ParameterizedTypeReference<List<Object>> TYPE_REF_LIST_OBJECT = new ParameterizedTypeReference<List<Object>>() { };

    private final URI URI_API_EMAILS;

    private final RestTemplate restTemplate = new RestTemplate();

    public UserService() {
        try {
            URI_API_EMAILS = new URI("https://api.github.com/user/emails");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> getUserEmails(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + accessToken);
        RequestEntity<String> entity1 = new RequestEntity<>(headers, HttpMethod.GET, URI_API_EMAILS);

        ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(entity1, TYPE_REF_LIST_OBJECT);

        if (responseEntity.getStatusCode().is4xxClientError() || responseEntity.getStatusCode().is5xxServerError()) {
            throw new ApiException(responseEntity);
        }

        LOGGER.info("API response [{}]", responseEntity);

        return responseEntity.getBody();
    }
}
