package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetSettingsRequest extends RippleRequest {
    @JsonProperty
    private AccountSettings settings;

    public SetSettingsRequest(String secret, String clientResourceId, AccountSettings settings) {
        super(secret, clientResourceId);
        this.settings = settings;
    }
}
