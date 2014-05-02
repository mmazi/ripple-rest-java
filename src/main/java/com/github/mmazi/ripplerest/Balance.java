package com.github.mmazi.ripplerest;

import java.io.Serializable;
import java.math.BigDecimal;

public class Balance implements Serializable {
    private String currency;
    private String counterparty;
    private BigDecimal value;

    public String getCurrency() {
        return currency;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public BigDecimal getValue() {
        return value;
    }
}
