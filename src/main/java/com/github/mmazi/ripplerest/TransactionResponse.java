package com.github.mmazi.ripplerest;

public class TransactionResponse extends RippleResponse<Transaction> {

    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public Transaction getValue() {
        return getTransaction();
    }
}
