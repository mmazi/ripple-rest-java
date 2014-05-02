package com.github.mmazi.ripplerest;

public class UuidResponse extends RippleResponse<String> {

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    @Override
    public String getValue() {
        return uuid;
    }
}
