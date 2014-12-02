package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectedResponse extends RippleResponse<Boolean> {

    @JsonProperty
    private boolean connected;

    public ConnectedResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public boolean isConnected() {
        return connected;
    }

    @Override
    public Boolean getValue() {
        return connected;
    }
}
