
package com.github.mmazi.ripplerest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notification implements Serializable {

    /**
     * A Ripple account address
     */
    @JsonProperty("account")
    @Pattern(regexp = "^r[1-9A-HJ-NP-Za-km-z]{25,33}$")
    private String account;

    /**
     * The resource type this notification corresponds to. Possible values are "payment", "order", "trustline", "accountsettings"
     */
    @JsonProperty("type")
    private Type type;

    /**
     * The direction of the transaction, from the perspective of the account being queried. Possible values are "incoming", "outgoing", and "passthrough"
     */
    @JsonProperty("direction")
    private Direction direction;

    /**
     * The state of the transaction from the perspective of the Ripple Ledger. Possible values are "validated" and "failed"
     */
    @JsonProperty("state")
    private State state;

    /**
     * The rippled code indicating the success or failure type of the transaction. The code "tesSUCCESS" indicates that the transaction was successfully validated and written into the Ripple Ledger. All other codes will begin with the following prefixes: "tec", "tef", "tel", or "tej"
     */
    @JsonProperty("result")
    @Pattern(regexp = "te[cfjlms][A-Za-z_]+")
    private String result;

    /**
     * The index number of the ledger containing the validated or failed transaction. Failed payments will only be written into the Ripple Ledger if they fail after submission to a rippled and a Ripple Network fee is claimed
     */
    @JsonProperty("ledger")
    @Pattern(regexp = "^[0-9]+$")
    private Long ledger;

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
    private Date timestamp;

    /**
     * A URL that can be used to fetch the full resource this notification corresponds to
     */
    @JsonProperty("transaction_url")
    private String transactionUrl;

    /**
     * A URL that can be used to fetch the notification that preceded this one chronologically
     */
    @JsonProperty("previous_notification_url")
    private String previousNotificationUrl;

    /**
     * A URL that can be used to fetch the notification that followed this one chronologically
     */
    @JsonProperty("next_notification_url")
    private String nextNotificationUrl;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getAccount() {
        return account;
    }

    public Type getType() {
        return type;
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

    public Long getLedger() {
        return ledger;
    }

    public String getHash() {
        return hash;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTransactionUrl() {
        return transactionUrl;
    }

    public String getPreviousNotificationUrl() {
        return previousNotificationUrl;
    }

    public String getNextNotificationUrl() {
        return nextNotificationUrl;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static enum Type {
        payment, order, trustline, accountsettings
    }

    private static enum Direction {
        incoming, outgoing, passthrough
    }

    private static enum State {
        validated, failed
    }
}
