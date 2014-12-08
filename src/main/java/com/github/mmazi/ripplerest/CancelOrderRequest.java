package com.github.mmazi.ripplerest;

public class CancelOrderRequest extends RippleRequest {

    public CancelOrderRequest(String secret, String clientResourceId) {
        super(secret, clientResourceId);
    }
}
