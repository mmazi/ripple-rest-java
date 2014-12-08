package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.mmazi.ripplerest.util.AmountSlashSerializer;
import com.github.mmazi.ripplerest.util.LongNullDeserializer;

import java.math.BigDecimal;

public class Order {

    private String hash;

    @JsonDeserialize(using = LongNullDeserializer.class)
    private Long ledger;

    private String state;

    private String account;

    private BigDecimal fee;

    private Integer sequence;

    @JsonProperty("offer_sequence")
    private Integer offerSequence;

    @JsonProperty("taker_gets")
    @JsonSerialize(using = AmountSlashSerializer.class)
    private Amount takerGets;

    @JsonProperty("taker_pays")
    @JsonSerialize(using = AmountSlashSerializer.class)
    private Amount takerPays;

    @JsonProperty("type")
    private Type type;

    protected Order() { }

    public Order(Amount takerGets, Amount takerPays, Type type) {
        this.takerGets = takerGets;
        this.takerPays = takerPays;
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public Long getLedger() {
        return ledger;
    }

    public String getState() {
        return state;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public Integer getSequence() {
        return sequence;
    }

    public Amount getTakerGets() {
        return takerGets;
    }

    public Amount getTakerPays() {
        return takerPays;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Order{hash='%s', ledger=%d, state='%s', account='%s', fee=%s, sequence=%d, takerGets=%s, takerPays=%s, type=%s}",
                hash, ledger, state, account, fee, sequence, Amount.toStringSlashes(takerGets), Amount.toStringSlashes(takerPays), type);
    }

    public static enum Type { buy, sell }

}
