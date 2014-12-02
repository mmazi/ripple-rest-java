package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;
import si.mazi.rescu.HttpStatusExceptionSupport;

public class RippleException extends HttpStatusExceptionSupport {

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("error")
    private String error;

    @JsonProperty("client_resource_id")
    private String clientResourceId;

    public RippleException() { }

    public RippleException(@JsonProperty("error") String error, @JsonProperty("message") String message) {
        super(message != null ? message : error);
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getClientResourceId() {
        return clientResourceId;
    }

    public String getError() {
        return error;
    }
}
