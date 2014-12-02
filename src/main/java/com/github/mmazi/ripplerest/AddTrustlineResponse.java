package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTrustlineResponse extends RippleResponse<Trustline> {

    private String hash;

    private Long ledger;

    private Trustline trustline;

    public AddTrustlineResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Trustline getTrustline() {
        return trustline;
    }

    public String getHash() {
        return hash;
    }

    public Long getLedger() {
        return ledger;
    }

    @Override
    public Trustline getValue() {
        return getTrustline();
    }
}
