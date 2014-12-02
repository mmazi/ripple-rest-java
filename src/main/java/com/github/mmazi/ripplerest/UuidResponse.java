package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UuidResponse extends RippleResponse<String> {

    private String uuid;

    public UuidResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public String getValue() {
        return uuid;
    }
}
