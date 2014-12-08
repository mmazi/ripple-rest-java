package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceOrderRequest extends RippleRequest {

    @JsonProperty("order")
    private final Order order;

    public PlaceOrderRequest(String secret, String clientResourceId, Order order) {
        super(secret, clientResourceId);
        this.order = order;
    }
}
