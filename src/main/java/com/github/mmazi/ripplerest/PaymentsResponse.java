package com.github.mmazi.ripplerest;

import java.util.List;

public class PaymentsResponse extends RippleResponse<List<PaymentWithId>> {

    private List<PaymentWithId> payments;

    public List<PaymentWithId> getPayments() {
        return payments;
    }

    @Override
    public List<PaymentWithId> getValue() {
        return getPayments();
    }
}
