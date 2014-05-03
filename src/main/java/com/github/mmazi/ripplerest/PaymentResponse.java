package com.github.mmazi.ripplerest;

public class PaymentResponse extends RippleResponse<Payment> {

    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    @Override
    public Payment getValue() {
        return getPayment();
    }
}
