package com.github.mmazi.ripplerest;

import java.util.List;

public class PathsResponse extends RippleResponse<List<Payment>> {

    private List<Payment> payments;

    public List<Payment> getPayments() {
        return payments;
    }

    @Override
    public List<Payment> getValue() {
        return getPayments();
    }
}
