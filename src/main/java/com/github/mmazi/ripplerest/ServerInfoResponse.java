package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerInfoResponse extends RippleResponse<RippledServerStatus> {

    @JsonProperty("api_server_status")
    private String apiServerStatus;

    @JsonProperty("rippled_server_url")
    private String rippledServerUrl;

    @JsonProperty("rippled_server_status")
    @Valid
    private RippledServerStatus rippledServerStatus;

    @JsonProperty("api_documentation_url")
    private String apiDocumentationUrl;

    public String getApiServerStatus() {
        return apiServerStatus;
    }

    public String getRippledServerUrl() {
        return rippledServerUrl;
    }

    public RippledServerStatus getRippledServerStatus() {
        return rippledServerStatus;
    }

    public String getApiDocumentationUrl() {
        return apiDocumentationUrl;
    }

    @Override
    public RippledServerStatus getValue() {
        return getRippledServerStatus();
    }
}



