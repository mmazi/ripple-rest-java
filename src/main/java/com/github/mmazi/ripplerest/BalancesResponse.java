package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.mmazi.ripplerest.util.LongNullDeserializer;

import java.util.List;

public class BalancesResponse extends RippleResponse<List<Amount>> {

    private List<Amount> balances;

    @JsonDeserialize(using = LongNullDeserializer.class)
    private Long ledger;

    private Boolean validated;

    public BalancesResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public List<Amount> getBalances() {
        return balances;
    }

    public Long getLedger() {
        return ledger;
    }

    public Boolean getValidated() {
        return validated;
    }

    @Override
    public List<Amount> getValue() {
        return balances;
    }
}

