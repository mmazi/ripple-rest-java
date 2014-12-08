package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.ExceptionalReturnContentException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class RippleResponse<V> implements Serializable, HasAdditionalProperties {

    private Boolean success;

    @JsonProperty("client_resource_id")
    private String clientResourceId;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    protected RippleResponse(Boolean success, String clientResourceId) {
        if (Boolean.FALSE.equals(success)) {
            throw new ExceptionalReturnContentException("Success was false.");
        }
        this.success = success;
        this.clientResourceId = clientResourceId;
    }

    public abstract V getValue();

    public Boolean getSuccess() {
        return success;
    }

    public String getClientResourceId() {
        return clientResourceId;
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

    @Override
    public String toString() {
        return String.format("RippleResponse{success=%s, clientResourceId='%s', value='%s'}",
                success, clientResourceId, getValue());
    }
}
