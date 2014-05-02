package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amount implements Serializable {

    private String currency;

//    @Pattern(regexp = "^$|^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String counterparty;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal value;

    protected Amount() {
    }

    public Amount(BigDecimal value, String currency, String counterparty) {
        this.currency = currency;
        this.counterparty = counterparty;
        this.value = value;
    }

    public Amount(BigDecimal value, String currency) {
        this(value, currency, null);
    }

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
