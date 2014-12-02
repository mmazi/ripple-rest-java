package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResponse extends RippleResponse<Transaction> {

    private Transaction transaction;

    public TransactionResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public Transaction getValue() {
        return getTransaction();
    }
}
