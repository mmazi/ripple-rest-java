package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentResponse extends RippleResponse<Payment> {

    private Payment payment;

    public PaymentResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public Payment getValue() {
        return getPayment();
    }
}
