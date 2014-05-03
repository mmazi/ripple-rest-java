package com.github.mmazi.ripplerest;

public class SettingsResponse extends RippleResponse<AccountSettings> {

    private AccountSettings settings;

    private String hash;

    private Long ledger;

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
