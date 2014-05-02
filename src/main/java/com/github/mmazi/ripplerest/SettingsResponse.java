package com.github.mmazi.ripplerest;

public class SettingsResponse extends RippleResponse<AccountSettings> {

    private AccountSettings settings;

    public AccountSettings getSettings() {
        return settings;
    }

    @Override
    public AccountSettings getValue() {
        return settings;
    }
}
