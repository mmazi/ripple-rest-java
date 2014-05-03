package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePaymentResponse extends RippleResponse<String> {
    @JsonProperty("status_url")
    private String statusUrl;

    public String getStatusUrl() {
        return statusUrl;
    }

    @Override
    public String getValue() {
        return getStatusUrl();
    }
}
