package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amount implements Serializable {

    private static final String SEPARATOR_PLUS = "+";

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

    public Amount(String str) {
        final String[] split = str.split("/");
        value = new BigDecimal(split[0]);
        if (split.length > 1) {
            currency = split[1];
            if (split.length > 2) {
                counterparty = split[2];
            }
        }
    }

    public Amount(BigDecimal value, String currency) {
        this(value, currency, null);
    }

    static String toStringSlashes(Amount amount) {
        return amount == null ? null : amount.toStringSlashes();
    }

    public String getCurrency() {
        return currency;
    }

    public String getCounterparty() {
        return counterparty;
    }

    @JsonProperty("issuer")
    private void setIssuer(String issuer) {
        counterparty = "".equals(issuer) ? null : issuer;
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

    public String toStringSlashes() {
        // Note that couterpary-less amounts are considered to be XRP and are formatted without explicit currency string.
        return counterparty == null ?  String.format("%s", value) : String.format("%s%s%s%s%s", value, "/", currency, "/", counterparty);
    }

    @Override
    public String toString() {
        return counterparty == null ?  String.format("%s%s%s", value, SEPARATOR_PLUS, currency) : String.format("%s%s%s%s%s", value, SEPARATOR_PLUS, currency, SEPARATOR_PLUS, counterparty);
    }
}
