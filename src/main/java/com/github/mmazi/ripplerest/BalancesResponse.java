package com.github.mmazi.ripplerest;

import java.util.List;

public class BalancesResponse extends RippleResponse<List<Amount>> {
    private List<Amount> balances;

    public List<Amount> getBalances() {
        return balances;
    }

    @Override
    public List<Amount> getValue() {
        return balances;
    }
}

