package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse extends RippleResponse<Order> {

    @JsonProperty
    private Order order;

    public OrderResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public Order getValue() {
        return order;
    }
}
