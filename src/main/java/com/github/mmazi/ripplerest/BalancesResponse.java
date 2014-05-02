package com.github.mmazi.ripplerest;

import java.util.List;

public class BalancesResponse extends RippleResponse {
    private List<Balance> balances;

    public List<Balance> getBalances() {
        return balances;
    }
}

