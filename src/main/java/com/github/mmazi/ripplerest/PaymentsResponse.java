package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentsResponse extends RippleResponse<List<PaymentWithId>> {

    private List<PaymentWithId> payments;

    public PaymentsResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public List<PaymentWithId> getPayments() {
        return payments;
    }

    @Override
    public List<PaymentWithId> getValue() {
        return getPayments();
    }
}
