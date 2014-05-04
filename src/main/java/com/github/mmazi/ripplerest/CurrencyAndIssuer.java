package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class is immutable.
 */
public class CurrencyAndIssuer implements Serializable {

    public static final CurrencyAndIssuer XRP = new CurrencyAndIssuer("XRP", null);

    public static final String XRP_SYMBOL = "XRP";

    /**
     * The three-character code or hex string used to denote currencies
     */
    @JsonProperty("currency")
    @Pattern(regexp = "^([a-zA-Z0-9]{3}|[A-Fa-f0-9]{40})$")
    private String currency;

    /**
     * The Ripple account address of the currency's issuer or gateway, or an empty string if the currency is XRP
     */
    @JsonProperty("issuer")
    @Pattern(regexp = "^$|^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String issuer;

    private CurrencyAndIssuer(String currency, String issuer) {
        if (currency == null) {
            throw new IllegalArgumentException("Currency must not be null.");
        }
        this.currency = currency;
        this.issuer = issuer;
    }

    public static CurrencyAndIssuer instance(String currency, String issuer) {
        return new CurrencyAndIssuer(currency, issuer);
    }

    public static CurrencyAndIssuer instance(String currency) {
        return XRP_SYMBOL.equals(currency) ? XRP : new CurrencyAndIssuer(currency, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyAndIssuer that = (CurrencyAndIssuer) o;

        return Objects.equals(this.currency, that.currency) &&
                Objects.equals(this.issuer, that.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, issuer);
    }

    @Override
    public String toString() {
        return issuer == null ? currency : String.format("%s+%s", currency, issuer);
    }
}
