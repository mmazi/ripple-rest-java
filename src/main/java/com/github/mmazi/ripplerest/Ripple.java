package com.github.mmazi.ripplerest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

// todo: Consider creating a wrapper that unwraps the response
@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
interface Ripple {

    @GET
    @Path("accounts/{address}/balances")
    BalancesResponse getBalances(@PathParam("address") String address) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/settings")
    SettingsResponse getSettings(@PathParam("address") String address) throws RippleException, IOException;

    @POST
    @Path("accounts/{address}/settings")
    SettingsResponse setSettings(@PathParam("address") String address, SetSettingsRequest settings) throws RippleException, IOException;

    @POST
    @Path("accounts/{address}/payments")
    CreatePaymentResponse createPayment(@PathParam("address") String address, PaymentRequest paymentRequest) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/payments/paths/{destinationAccount}/{destinationAmount}?{sourceCurrencies}")
    PathsResponse findPaths(
            @PathParam("address") String address,
            @PathParam("destinationAccount") String destinationAccount,
            @PathParam("destinationAmount") Amount destinationAmount,
            @PathParam("sourceCurrencies") Currencies sourceCurrencies
    ) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/payments/{hash}")
    PaymentResponse getPayment(
            @PathParam("address") String address,
            @PathParam("hash") String hashOrClientResourceId
    ) throws RippleException, IOException;


    //  Todo: double-check with https://dev.ripple.com/#payment-history
    /**
     * @param sourceAccount      If specified, limit the results to payments initiated by a particular account
     * @param destinationAccount If specified, limit the results to payments made to a particular account
     * @param excludeFailed      if set to true, this will return only payment that were successfully validated and written into the Ripple Ledger
     * @param startLedger        If earliest_first is set to true this will be the index number of the earliest ledger queried, or the most recent one if earliest_first is set to false. Defaults to the first ledger the rippled has in its complete ledger. An error will be returned if this value is outside the rippled's complete ledger set
     * @param endLedger          If earliest_first is set to true this will be the index number of the most recent ledger queried, or the earliest one if earliest_first is set to false. Defaults to the last ledger the rippled has in its complete ledger. An error will be returned if this value is outside the rippled's complete ledger set
     * @param earliestFirst      Determines the order in which the results should be displayed. Defaults to true
     * @param resultsPerPage     Limits the number of resources displayed per page. Defaults to 20
     * @param page               The page to be displayed. If there are fewer than the results_per_page number displayed, this indicates that this is the last page
     */
    @GET
    @Path("accounts/{address}/payments")
    PaymentsResponse getPayments(
            @PathParam("address") String address,
            @QueryParam("source_account") String sourceAccount,
            @QueryParam("destination_account") String destinationAccount,
            @QueryParam("exclude_failed") Boolean excludeFailed,
            @QueryParam("start_ledger") Integer startLedger,
            @QueryParam("end_ledger") Integer endLedger,
            @QueryParam("earliest_first") Boolean earliestFirst,
            @QueryParam("results_per_page") Integer resultsPerPage,
            @QueryParam("page") Integer page
    ) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/trustlines")
    TrustlinesResponse getTrustlines(
            @PathParam("address") String address,
            @QueryParam("currency") String currency,
            @QueryParam("counterparty") String counterparty
    ) throws RippleException, IOException;
    
    @POST
    @Path("accounts/{address}/trustlines")
    AddTrustlineResponse addTrustline(
            @PathParam("address") String address, AddTrustlineRequest addTrustlineRequest
    ) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/notifications/{hash}")
    NotificationResponse getNotifictaion(
            @PathParam("address") String address, @PathParam("hash")String hash
    ) throws RippleException, IOException;

    @GET
//    @Path("tx/{hash}") // see https://dev.ripple.com/#retrieve-ripple-transaction
    @Path("transactions/{hash}")
    TransactionResponse getTransactionDetails(@PathParam("hash") String hash) throws RippleException, IOException;

    @GET
    @Path("server/connected")
    ConnectedResponse isServerConnected() throws RippleException, IOException;

    @GET
    @Path("server")
    ServerInfoResponse getServerInfo() throws RippleException, IOException;

    @GET
    @Path("uuid")
    UuidResponse generateUuid() throws RippleException, IOException;
}
