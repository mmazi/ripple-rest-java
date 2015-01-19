package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.mmazi.ripplerest.util.LongNullDeserializer;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * A simplified Trustline object used by the ripple-rest API
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class Trustline implements Serializable, HasAdditionalProperties {

    /**
     * A Ripple account address
     */
    @JsonProperty("account")
    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String account;

    /**
     * A Ripple account address
     */
    @JsonProperty("counterparty")
    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String counterparty;

    /**
     * Currency
     * <p/>
     * The three-character code or hex string used to denote currencies
     */
    @JsonProperty("currency")
    @Pattern(regexp = "^([a-zA-Z0-9]{3}|[A-Fa-f0-9]{40})$")
    private String currency;

    @JsonProperty("limit")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal limit;

    @JsonProperty("reciprocated_limit")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal reciprocatedLimit;

    /**
     * Set to true if the account has explicitly authorized the counterparty to hold currency it issues. This is only necessary if the account's settings include require_authorization_for_incoming_trustlines
     */
    @JsonProperty("authorized_by_account")
    private Boolean authorizedByAccount;

    /**
     * Set to true if the counterparty has explicitly authorized the account to hold currency it issues. This is only necessary if the counterparty's settings include require_authorization_for_incoming_trustlines
     */
    @JsonProperty("authorized_by_counterparty")
    private Boolean authorizedByCounterparty;

    /**
     * If true it indicates that the account allows pairwise rippling out through this trustline
     */
    @JsonProperty("account_allows_rippling")
    private Boolean accountAllowsRippling;

    @JsonProperty("account_froze_trustline")
    private Boolean accountFrozeTrustline;

    /**
     * If true it indicates that the counterparty allows pairwise rippling out through this trustline
     */
    @JsonProperty("counterparty_allows_rippling")
    private Boolean counterpartyAllowsRippling;

    /**
     * The string representation of the index number of the ledger containing this trustline or, in the case of historical queries, of the transaction that modified this Trustline
     */
    @JsonProperty("ledger")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LongNullDeserializer.class)
    private Long ledger;

    /**
     * Hash256
     * <p/>
     * The hex representation of a 256-bit hash
     */
    @JsonProperty("hash")
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String hash;

    @JsonProperty("previous")
    @Valid
    private Trustline previous;

    @JsonProperty("account_trustline_frozen")
    private Boolean accountTrustlineFrozen;

    @JsonProperty("state")
    private String state;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    ///////////////////////////////////////////////


    protected Trustline() {
    }

    public Trustline(BigDecimal limit, String currency, String counterparty) {
        this.counterparty = counterparty;
        this.currency = currency;
        this.limit = limit;
    }


    ///////////////////////////////////////////////


    public String getAccount() {
        return account;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getReciprocatedLimit() {
        return reciprocatedLimit;
    }

    public Boolean getAuthorizedByAccount() {
        return authorizedByAccount;
    }

    public Boolean getAuthorizedByCounterparty() {
        return authorizedByCounterparty;
    }

    public Boolean getAccountAllowsRippling() {
        return accountAllowsRippling;
    }

    public Boolean getAccountFrozeTrustline() {
        return accountFrozeTrustline;
    }

    public Boolean getCounterpartyAllowsRippling() {
        return counterpartyAllowsRippling;
    }

    public Long getLedger() {
        return ledger;
    }

    public String getHash() {
        return hash;
    }

    public Trustline getPrevious() {
        return previous;
    }

    public Boolean getAccountTrustlineFrozen() {
        return accountTrustlineFrozen;
    }

    public String getState() {
        return state;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    ///////////////////////////////////////////////


    @Override
    public String toString() {
        return "Trustline[" +
                "account='" + account + '\'' +
                ", counterparty='" + counterparty + '\'' +
                ", currency='" + currency + '\'' +
                ", limit=" + limit +
                ", reciprocatedLimit=" + reciprocatedLimit +
                ", authorizedByAccount=" + authorizedByAccount +
                ", authorizedByCounterparty=" + authorizedByCounterparty +
                ", accountAllowsRippling=" + accountAllowsRippling +
                ", accountFrozeTrustline=" + accountFrozeTrustline +
                ", counterpartyAllowsRippling=" + counterpartyAllowsRippling +
                ", ledger=" + ledger +
                ", hash='" + hash + '\'' +
                ", previous=" + previous +
                ", additionalProperties=" + additionalProperties +
                ']';
    }
}
