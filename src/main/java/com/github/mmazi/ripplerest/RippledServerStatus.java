package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RippledServerStatus implements Serializable {

    @JsonProperty("build_version")
    private String buildVersion;

    @JsonProperty("complete_ledgers")
    private String completeLedgers;

    @JsonProperty("hostid")
    private String hostid;

    @JsonProperty("last_close")
    @Valid
    private LastClose lastClose;

    @JsonProperty("load_factor")
    private Long loadFactor;

    @JsonProperty("peers")
    private Long peers;

    @JsonProperty("pubkey_node")
    private String pubkeyNode;

    @JsonProperty("server_state")
    private String serverState;

    @JsonProperty("validated_ledger")
    @Valid
    private ValidatedLedger validatedLedger;

    @JsonProperty("validation_quorum")
    private Long validationQuorum;

    public String getBuildVersion() {
        return buildVersion;
    }

    public String getCompleteLedgers() {
        return completeLedgers;
    }

    public String getHostid() {
        return hostid;
    }

    public LastClose getLastClose() {
        return lastClose;
    }

    public Long getLoadFactor() {
        return loadFactor;
    }

    public Long getPeers() {
        return peers;
    }

    public String getPubkeyNode() {
        return pubkeyNode;
    }

    public String getServerState() {
        return serverState;
    }

    public ValidatedLedger getValidatedLedger() {
        return validatedLedger;
    }

    public Long getValidationQuorum() {
        return validationQuorum;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LastClose implements Serializable {

        @JsonProperty("converge_time_s")
        private Float convergeTimeS;

        @JsonProperty("proposers")
        private Long proposers;

        public Float getConvergeTimeS() {
            return convergeTimeS;
        }

        public Long getProposers() {
            return proposers;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ValidatedLedger implements Serializable {

        @JsonProperty("age")
        private Long age;

        @JsonProperty("base_fee_xrp")
        private Float baseFeeXrp;

        @JsonProperty("hash")
        private String hash;

        @JsonProperty("reserve_base_xrp")
        private Long reserveBaseXrp;

        @JsonProperty("reserve_inc_xrp")
        private Long reserveIncXrp;

        @JsonProperty("seq")
        private Long seq;

        public Long getAge() {
            return age;
        }

        public Float getBaseFeeXrp() {
            return baseFeeXrp;
        }

        public String getHash() {
            return hash;
        }

        public Long getReserveBaseXrp() {
            return reserveBaseXrp;
        }

        public Long getReserveIncXrp() {
            return reserveIncXrp;
        }

        public Long getSeq() {
            return seq;
        }
    }
}
