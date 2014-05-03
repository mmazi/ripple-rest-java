package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

// todo: Try to figure out the actual types of the properties with no javadocs (currently Strings).

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountSettings {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // flags

    @JsonProperty("disable_master")
    private Boolean disableMaster = null;

    @JsonProperty("disallow_xrp")
    private Boolean disallowXrp = null;

    @JsonProperty("password_spent")
    private Boolean passwordSpent = null;

    @JsonProperty("require_authorization")
    private Boolean requireAuthorization = null;

    @JsonProperty("require_destination_tag")
    private Boolean requireDestinationTag = null;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // other fields

    /**
     * A string representation of the last sequence number of a validated transaction created by this account
     */
    @JsonProperty("transaction_sequence")
    private Long transactionSequence = null;

    /**
     * The MD5 128-bit hash of the account owner's email address
     */
    @JsonProperty("email_hash")
//    @Pattern(regexp = "^$|^[A-Fa-f0-9]{32}$")
    private String emailHash = null;

    @JsonProperty("wallet_locator")
    private String walletLocator = null;

    @JsonProperty("message_key")
    private String messageKey = null;

    @JsonProperty("domain")
    private String domain = null;

    /**
     * A string representation of the rate charged each time a holder of currency issued by this account transfers it. By default the rate is "1.0". A rate of "1.01" is a 1% charge on top of the amount being transferred. Up to nine decimal places are supported
     */
    @JsonProperty("transfer_rate")
    private BigDecimal transferRate = null;

    @JsonProperty("signers")
    private String signers = null;

    public Boolean getDisableMaster() {
        return disableMaster;
    }

    public void setDisableMaster(Boolean disableMaster) {
        this.disableMaster = disableMaster;
    }

    public Boolean getDisallowXrp() {
        return disallowXrp;
    }

    public void setDisallowXrp(Boolean disallowXrp) {
        this.disallowXrp = disallowXrp;
    }

    public Boolean getPasswordSpent() {
        return passwordSpent;
    }

    public void setPasswordSpent(Boolean passwordSpent) {
        this.passwordSpent = passwordSpent;
    }

    public Boolean getRequireAuthorization() {
        return requireAuthorization;
    }

    public void setRequireAuthorization(Boolean requireAuthorization) {
        this.requireAuthorization = requireAuthorization;
    }

    public Boolean getRequireDestinationTag() {
        return requireDestinationTag;
    }

    public void setRequireDestinationTag(Boolean requireDestinationTag) {
        this.requireDestinationTag = requireDestinationTag;
    }

    public Long getTransactionSequence() {
        return transactionSequence;
    }

    public void setTransactionSequence(Long transactionSequence) {
        this.transactionSequence = transactionSequence;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public String getWalletLocator() {
        return walletLocator;
    }

    public void setWalletLocator(String walletLocator) {
        this.walletLocator = walletLocator;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public BigDecimal getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(BigDecimal transferRate) {
        this.transferRate = transferRate;
    }

    public String getSigners() {
        return signers;
    }

    public void setSigners(String signers) {
        this.signers = signers;
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// The following settings fields are specified in https://github.com/emschwartz/ripple-rest/tree/master/schemas
// but not in https://github.com/ripple/ripple-rest/blob/develop/docs/api-reference.md#settings . May become
// used in the future; the current servers doesn't seem to support them.

/*

    */
/**
     * The Ripple address of the account in question
     *//*

    @JsonProperty("account")
//    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String account;

    */
/**
     * The hash of an optional additional public key that can be used for signing and verifying transactions
     *//*

    @JsonProperty("regular_key")
//    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String regularKey;

    */
/**
     * The domain associated with this account. The ripple.txt file can be looked up to verify this information
     *//*

    @JsonProperty("owner_url")
//    @Pattern(regexp = "^(ftp:\/\/|http:\/\/|https:\/\/)?([A-Za-z0-9_]+:{0,1}[A-Za-z0-9_]*@)?(^([ \t\r\n\f])+)(:[0-9]+)?(\/|\/([[A-Za-z0-9_]#!:.?+=&%@!-\/]))?$");
    private String ownerUrl;

    */
/**
     * An optional public key, represented as hex, that can be set to allow others to send encrypted messages to the account owner
     *//*

//    @Pattern(regexp = "^([0-9a-fA-F]{2}){0,33}$")
    @JsonProperty("message_public_key")
    private String messagePublicKey;

    */
/**
     * If set to true incoming payments will only be validated if they include a destination_tag. This may be used primarily by gateways that operate exclusively with hosted wallets
     *//*

    @JsonProperty("require_destination_tag_for_incoming_payments")
    private Boolean requireDestinationTagForIncomingPayments;

    */
/**
     * If set to true incoming trustlines will only be validated if this account first creates a trustline to the counterparty with the authorized flag set to true. This may be used by gateways to prevent accounts unknown to them from holding currencies they issue
     *//*

    @JsonProperty("require_authorization_for_incoming_trustlines")
    private Boolean requireAuthorizationForIncomingTrustlines;

    */
/**
     * If set to true incoming XRP payments will be allowed
     *//*

    @JsonProperty("allow_xrp_payments")
    private Boolean allowXrpPayments;

    */
/**
     * The number of trustlines owned by this account. This value does not include incoming trustlines where this account has not explicitly reciprocated trust
     *//*

    @JsonProperty("trustlines_owned")
    private Long trustlinesOwned;

    */
/**
     * The string representation of the index number of the ledger containing these account settings or, in the case of historical queries, of the transaction that modified these settings
     *//*

//    @Pattern(regexp = "^[0-9]+$")
    @JsonProperty("ledger")
    private Long ledger;

    */
/**
     * If this object was returned by a historical query this value will be the hash of the transaction that modified these settings. The transaction hash is used throughout the Ripple Protocol to uniquely identify a particular transaction
     *//*

    @JsonProperty("hash")
    //    @Pattern(regexp = "^$|^[A-Fa-f0-9]{64}$")
    private String hash;

    */
/**
     * If the account settings were changed this will be a full AccountSettings object representing the previous values. If the previous object also had a previous object that will be removed to reduce data complexity. AccountSettings changes can be walked backwards by querying the API for previous.hash repeatedly
     *//*

    @JsonProperty("previous")
    private AccountSettings previous;

*/

