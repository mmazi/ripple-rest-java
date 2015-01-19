package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;


/**
 * Payment
 * <p/>
 * A flattened Payment object used by the ripple-rest API
 *
 * // todo: update javadocs from https://dev.ripple.com/#preparing-a-payment
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
public class Payment implements Serializable, HasAdditionalProperties {

    /**
     * RippleAddress
     * <p/>
     * A Ripple account address
     */
    @JsonProperty("source_account")
    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String sourceAccount;

    @JsonProperty("source_tag")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceTag;

    /**
     * Amount
     * <p/>
     * An Amount on the Ripple Protocol, used also for XRP in the ripple-rest API
     */
    @JsonProperty("source_amount")
    @Valid
    private Amount sourceAmount;

    @JsonProperty("source_slippage")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal sourceSlippage;

    /**
     * RippleAddress
     * <p/>
     * A Ripple account address
     */
    @JsonProperty("destination_account")
    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String destinationAccount;

    /**
     * UINT32
     * <p/>
     * An unsigned 32-bit integer (0-4294967295)
     */
    @JsonProperty("destination_tag")
//    @Pattern(regexp = "^$|^(429496729[0-5]|42949672[0-8][0-9]|4294967[01][0-9]{2}|429496[0-6][0-9]{3}|42949[0-5][0-9]{4}|4294[0-8][0-9]{5}|429[0-3][0-9]{6}|42[0-8][0-9]{7}|4[01][0-9]{8}|[1-3][0-9]{9}|[1-9][0-9]{8}|[1-9][0-9]{7}|[1-9][0-9]{6}|[1-9][0-9]{5}|[1-9][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[0-9])$")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long destinationTag;

    /**
     * Amount
     * <p/>
     * An Amount on the Ripple Protocol, used also for XRP in the ripple-rest API
     */
    @JsonProperty("destination_amount")
    @Valid
    private Amount destinationAmount;

    /**
     * Hash256
     * <p/>
     * The hex representation of a 256-bit hash
     */
    @JsonProperty("invoice_id")
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String invoiceId;

    /**
     * A "stringified" version of the Ripple PathSet structure that users should treat as opaque
     */
    @JsonProperty("paths")
    private String paths;

    /**
     * A boolean that, if set to true, indicates that this payment should go through even if the whole amount cannot be delivered because of a lack of liquidity or funds in the source_account account
     */
    @JsonProperty("partial_payment")
    private Boolean partialPayment;

    /**
     * A boolean that can be set to true if paths are specified and the sender would like the Ripple Network to disregard any direct paths from the source_account to the destination_account. This may be used to take advantage of an arbitrage opportunity or by gateways wishing to issue balances from a hot wallet to a user who has mistakenly set a trustline directly to the hot wallet
     */
    @JsonProperty("no_direct_ripple")
    private Boolean noDirectRipple;

    /**
     * The direction of the payment, from the perspective of the account being queried. Possible values are "incoming", "outgoing", and "passthrough"
     */
    @JsonProperty("direction")
    private Direction direction;

    /**
     * The state of the payment from the perspective of the Ripple Ledger. Possible values are "validated" and "failed" and "new" if the payment has not been submitted yet
     */
    @JsonProperty("state")
    private State state;

    /**
     * The rippled code indicating the success or failure type of the payment. The code "tesSUCCESS" indicates that the payment was successfully validated and written into the Ripple Ledger. All other codes will begin with the following prefixes: "tec", "tef", "tel", or "tej"
     */
    @JsonProperty("result")
    @Pattern(regexp = "te[cfjlms][A-Za-z_]+")
    private String result;

    /**
     * Hash256
     * <p/>
     * The hex representation of a 256-bit hash
     */
    @JsonProperty("hash")
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String hash;

    /**
     * Timestamp
     * <p/>
     * An ISO 8601 combined date and time timestamp
     */
    @JsonProperty("timestamp")
//    @Pattern(regexp = "^$|^[0-9]{4}-[0-1][0-9]-[0-3][0-9]T(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9](Z|[+](2[0-3]|[01][0-9]):[0-5][0-9])$")
    private Date timestamp;

    @JsonProperty("fee")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal fee;

    /**
     * Parsed from the validated transaction metadata, this array represents all of the changes to balances held by the source_account. Most often this will have one amount representing the Ripple Network fee and, if the source_amount was not XRP, one amount representing the actual source_amount that was sent
     */
    @JsonProperty("source_balance_changes")
    @Valid
    private List<Amount> sourceBalanceChanges = new ArrayList<Amount>();

    /**
     * Parsed from the validated transaction metadata, this array represents the changes to balances held by the destination_account. For those receiving payments this is important to check because if the partial_payment flag is set this value may be less than the destination_amount
     */
    @JsonProperty("destination_balance_changes")
    @Valid
    private List<Amount> destinationBalanceChanges = new ArrayList<Amount>();

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected Payment() {
    }

    public Payment(String sourceAccount, String destinationAccount, Amount destinationAmount) {
        this(sourceAccount, destinationAccount, destinationAmount, null, null, null, null, null, null, null, null);
    }

    public Payment(String sourceAccount, String destinationAccount, Amount destinationAmount, Amount sourceAmount, BigDecimal sourceSlippage, Long sourceTag, Long destinationTag, String invoiceId, Boolean partialPayment, Boolean noDirectRipple, String paths) {
        this.sourceAccount = sourceAccount;
        this.sourceTag = sourceTag;
        this.sourceAmount = sourceAmount;
        this.sourceSlippage = sourceSlippage;
        this.destinationAccount = destinationAccount;
        this.destinationTag = destinationTag;
        this.destinationAmount = destinationAmount;
        this.invoiceId = invoiceId;
        this.paths = paths;
        this.partialPayment = partialPayment;
        this.noDirectRipple = noDirectRipple;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public Long getSourceTag() {
        return sourceTag;
    }

    public Amount getSourceAmount() {
        return sourceAmount;
    }

    public BigDecimal getSourceSlippage() {
        return sourceSlippage;
    }

    public String getDestinationAccount() {
        return destinationAccount;
    }

    public Long getDestinationTag() {
        return destinationTag;
    }

    public Amount getDestinationAmount() {
        return destinationAmount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public String getPaths() {
        return paths;
    }

    public Boolean getPartialPayment() {
        return partialPayment;
    }

    public Boolean getNoDirectRipple() {
        return noDirectRipple;
    }

    public Direction getDirection() {
        return direction;
    }

    public State getState() {
        return state;
    }

    public String getResult() {
        return result;
    }

    public String getHash() {
        return hash;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public List<Amount> getSourceBalanceChanges() {
        return sourceBalanceChanges;
    }

    public List<Amount> getDestinationBalanceChanges() {
        return destinationBalanceChanges;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Payment[" +
                "hash='" + hash + '\'' +
                ", timestamp=" + timestamp +
                ", sourceAccount='" + sourceAccount + '\'' +
                ", sourceTag=" + sourceTag +
                ", sourceAmount=" + sourceAmount +
                ", sourceSlippage=" + sourceSlippage +
                ", destinationAccount='" + destinationAccount + '\'' +
                ", destinationTag=" + destinationTag +
                ", destinationAmount=" + destinationAmount +
                ", invoiceId='" + invoiceId + '\'' +
                ", paths='" + paths + '\'' +
                ", partialPayment=" + partialPayment +
                ", noDirectRipple=" + noDirectRipple +
                ", direction=" + direction +
                ", state=" + state +
                ", result='" + result + '\'' +
                ", fee=" + fee +
                ", sourceBalanceChanges=" + sourceBalanceChanges +
                ", destinationBalanceChanges=" + destinationBalanceChanges +
                ", additionalProperties=" + additionalProperties +
                ']';
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static enum Direction {
        incoming, outgoing, passthrough;
    }

    public static enum State {
        pending, validated, failed;
    }
}
