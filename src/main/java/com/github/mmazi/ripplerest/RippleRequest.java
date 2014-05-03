package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public abstract class RippleRequest implements Serializable {

    @JsonProperty("secret")
    private String secret;

    @JsonProperty("client_resource_id")
    private String clientResourceId;

    protected RippleRequest(String secret, String clientResourceId) {
        this.secret = secret;
        this.clientResourceId = clientResourceId;
    }

    public String getSecret() {
        return secret;
    }

    public String getClientResourceId() {
        return clientResourceId;
    }
}
