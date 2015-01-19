package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.mmazi.ripplerest.util.LongNullDeserializer;

import javax.validation.constraints.Pattern;

public class PaymentResponse extends RippleResponse<Payment> {

    private Payment payment;

    @Pattern(regexp = "^[0-9]+$")
    @JsonProperty("ledger")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LongNullDeserializer.class)
    private Long ledger;

    @JsonProperty("hash")
    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String hash;

    @JsonProperty("state")
    private String state;

    public PaymentResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public Payment getValue() {
        return getPayment();
    }

    public Long getLedger() {
        return ledger;
    }

    public String getHash() {
        return hash;
    }

    public String getState() {
        return state;
    }
}
