package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction implements Serializable, HasAdditionalProperties {

    /**
     * The signature for the transaction.
     */
    @JsonProperty("TxnSignature")
    @Nullable
    String txnSignature;

    @JsonProperty("Amount")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal amount;

    @JsonProperty("Destination")
    @Pattern(regexp = "^$|^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String destination;

    @JsonProperty("hash")
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String hash;

    @JsonProperty("inLedger")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long inLedger;

    @JsonProperty("ledger_index")
    private Long ledgerIndex;

    @JsonProperty("validated")
    private Boolean validated;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("meta")
    private Map<String, Object> meta;

    /**
     * The type of transaction
     */
    @JsonProperty("TransactionType")
    @NotNull
//    private Long transactionType;
    private String transactionType;

    /**
     * Selects transaction options
     */
    @JsonProperty("Flags")
    @Nullable
    @JsonSerialize(using = ToStringSerializer.class)
    private Long flags;

    /**
     * Allows the creator of transaction to identify the transaction. For payments, specify the DestinationTag for returning funds.
     */
    @JsonProperty("SourceTag")
    @Nullable
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sourceTag;

    /**
     * Allows the creator to include additional information to identify the transaction.
     */
    @JsonProperty("Memos")
    @Nullable
    private List memos;

    /**
     * The account issuing the transaction
     */
    @JsonProperty("Account")
    @NotNull
    private String account;

    /**
     * The transaction's sequence number
     */
    @JsonProperty("Sequence")
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sequence;

    /**
     * The ID of the previous transaction
     */
    @JsonProperty("PreviousTxnID")
    @Nullable
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String previousTxnID;

    /**
     * Highest valid ledger number that a transaction can appear in.
     */
    @JsonProperty("LastLedgerSequence")
    @Nullable
    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastLedgerSequence;

    /**
     * The transaction fee
     */
    @JsonProperty("Fee")
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private Long fee;

    /**
     * The public key matching the private key used to sign the transaction.
     */
    @JsonProperty("SigningPubKey")
    @NotNull
    private String signingPubKey;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /////////////////////////////////////////////////////////////////////////////////////////


    public BigDecimal getAmount() {
        return amount;
    }

    public String getDestination() {
        return destination;
    }

    public String getHash() {
        return hash;
    }

    public Long getInLedger() {
        return inLedger;
    }

    public Long getLedgerIndex() {
        return ledgerIndex;
    }

    public Boolean getValidated() {
        return validated;
    }

    public Date getDate() {
        return date;
    }

//    public Long getTransactionType() {
    public String getTransactionType() {
        return transactionType;
    }

    @Nullable
    public Long getFlags() {
        return flags;
    }

    @Nullable
    public Long getSourceTag() {
        return sourceTag;
    }

    @Nullable
    public List getMemos() {
        return memos;
    }

    public String getAccount() {
        return account;
    }

    public Long getSequence() {
        return sequence;
    }

    @Nullable
    public String getPreviousTxnID() {
        return previousTxnID;
    }

    @Nullable
    public Long getLastLedgerSequence() {
        return lastLedgerSequence;
    }

    public Long getFee() {
        return fee;
    }

    public String getSigningPubKey() {
        return signingPubKey;
    }

    @Nullable
    public String getTxnSignature() {
        return txnSignature;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    /////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return "Transaction[" +
                "txnSignature='" + txnSignature + '\'' +
                ", amount=" + amount +
                ", destination='" + destination + '\'' +
                ", hash='" + hash + '\'' +
                ", inLedger=" + inLedger +
                ", ledgerIndex=" + ledgerIndex +
                ", validated=" + validated +
                ", date=" + date +
                ", meta=" + meta +
                ", transactionType='" + transactionType + '\'' +
                ", flags=" + flags +
                ", sourceTag=" + sourceTag +
                ", memos=" + memos +
                ", account='" + account + '\'' +
                ", sequence=" + sequence +
                ", previousTxnID='" + previousTxnID + '\'' +
                ", lastLedgerSequence=" + lastLedgerSequence +
                ", fee=" + fee +
                ", signingPubKey='" + signingPubKey + '\'' +
                ", additionalProperties=" + additionalProperties +
                ']';
    }
}