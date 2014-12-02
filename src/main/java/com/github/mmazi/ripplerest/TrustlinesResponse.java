package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrustlinesResponse extends RippleResponse<List<Trustline>> {

    private List<Trustline> trustlines;

    public TrustlinesResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public List<Trustline> getTrustlines() {
        return trustlines;
    }

    @Override
    public List<Trustline> getValue() {
        return getTrustlines();
    }
}
