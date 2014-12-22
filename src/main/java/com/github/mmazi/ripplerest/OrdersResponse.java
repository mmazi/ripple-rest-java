package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrdersResponse extends RippleResponse<Order[]> {

    private Order[] orders;

    private Long ledger;

    private Boolean validated;

    public OrdersResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Order[] getOrders() {
        return orders;
    }

    public Long getLedger() {
        return ledger;
    }

    public Boolean getValidated() {
        return validated;
    }

    @Override
    public Order[] getValue() {
        return orders;
    }
}
