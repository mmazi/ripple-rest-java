package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amount implements Serializable {

    private String currency;

    @Pattern(regexp = "^$|^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount that = (Amount) o;

        return Objects.equals(this.currency, that.currency) &&
                Objects.equals(this.counterparty, that.counterparty) &&
                Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, counterparty, value);
    }

    @Override
    public String toString() {
        return counterparty == null ?  String.format("%s+%s", value, currency) : String.format("%s+%s+%s", value, currency, counterparty);
    }
}
