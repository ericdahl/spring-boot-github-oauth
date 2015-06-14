package com.github.ericdahl.spring_boot_github_oauth.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenRequest {

    private final String clientId;
    private final String clientSecret;
    private final String code;

    public AccessTokenRequest(String clientId, String clientSecret, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    @JsonProperty("client_id")
    public String getClientId() {
        return clientId;
    }


    @JsonProperty("client_secret")
    public String getClientSecret() {
        return clientSecret;
    }

    public String getCode() {
        return code;
    }
}
