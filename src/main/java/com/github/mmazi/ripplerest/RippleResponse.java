package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public abstract class RippleResponse<V> implements Serializable {
    private Boolean success;

    private String error;

    private String message;

    @JsonProperty("client_resource_id")
    private String clientResourceId;

    public abstract V getValue();

    public Boolean getSuccess() {
        return success;
    }

    public String getClientResourceId() {
        return clientResourceId;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
