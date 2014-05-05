package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class RippleResponse<V> implements Serializable, HasAdditionalProperties {
    private Boolean success;

    private String error;

    private String message;

    @JsonProperty("client_resource_id")
    private String clientResourceId;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @Override
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @Override
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
