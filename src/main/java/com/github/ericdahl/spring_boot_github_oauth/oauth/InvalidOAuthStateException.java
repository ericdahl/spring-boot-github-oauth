package com.github.ericdahl.spring_boot_github_oauth.oauth;

public class InvalidOAuthStateException extends RuntimeException {
    private final long sessionState;
    private final long returnedState;

    public InvalidOAuthStateException(long returnedState, long sessionState) {
        this.returnedState = returnedState;
        this.sessionState = sessionState;
    }

    @Override
    public String getMessage() {
        return "Session State [" + sessionState + "] did not match returned returnedState [" + returnedState + "]";
    }
}
