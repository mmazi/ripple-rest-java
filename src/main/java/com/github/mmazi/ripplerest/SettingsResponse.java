package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsResponse extends RippleResponse<AccountSettings> {

    private AccountSettings settings;

    private String hash;

    private Long ledger;

    public SettingsResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public AccountSettings getSettings() {
        return settings;
    }

    @Override
    public AccountSettings getValue() {
        return settings;
    }

    public String getHash() {
        return hash;
    }

    public Long getLedger() {
        return ledger;
    }
}
