package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.mmazi.ripplerest.util.LongNullDeserializer;

import javax.validation.constraints.Pattern;

public class AddTrustlineResponse extends RippleResponse<Trustline> {

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

    private Trustline trustline;

    public AddTrustlineResponse(@JsonProperty("success") Boolean success, @JsonProperty("client_resource_id") String crid) {
        super(success, crid);
    }

    public Trustline getTrustline() {
        return trustline;
    }

    public String getHash() {
        return hash;
    }

    public Long getLedger() {
        return ledger;
    }

    @Override
    public Trustline getValue() {
        return getTrustline();
    }
}
