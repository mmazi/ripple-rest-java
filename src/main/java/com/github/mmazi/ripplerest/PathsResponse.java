package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PathsResponse extends RippleResponse<List<Payment>> {

    private List<Payment> payments;

    public PathsResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public List<Payment> getPayments() {
        return payments;
    }

    @Override
    public List<Payment> getValue() {
        return getPayments();
    }
}
