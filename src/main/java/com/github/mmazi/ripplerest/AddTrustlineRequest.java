package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({"secret", "client_resource_id", "payment" })
public class AddTrustlineRequest extends RippleRequest {

    @JsonProperty("allow_rippling")
    private final Boolean allowRippling;

    @JsonProperty("trustline")
    private final Trustline trustline;

    private AddTrustlineRequest(String secret, String clientResourceId, Boolean allowRippling, Trustline trustline) {
        super(secret, clientResourceId);
        this.allowRippling = allowRippling;
        this.trustline = trustline;
    }

    public static AddTrustlineRequest create(String secret, Boolean allowRippling, Trustline trustlineAmount, String clientResourceId) {
        return new AddTrustlineRequest(secret, clientResourceId, allowRippling, trustlineAmount);
    }

}
