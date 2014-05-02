package com.github.mmazi.ripplerest;

import java.util.List;

public class BalancesResponse extends RippleResponse<List<Balance>> {
    private List<Balance> balances;

    public List<Balance> getBalances() {
        return balances;
    }

    @Override
    public List<Balance> getValue() {
        return balances;
    }
}

