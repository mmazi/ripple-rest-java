package com.github.mmazi.ripplerest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Ripple {

    @GET
    @Path("accounts/{address}/balances")
    public BalancesResponse getBalances(@PathParam("address") String address) throws RippleException, IOException;

    @GET
    @Path("accounts/{address}/settings")
    public SettingsResponse getSettings(@PathParam("address") String address) throws RippleException, IOException;

    @POST
    @Path("accounts/{address}/settings")
    public SettingsResponse setSettings(@PathParam("address") String address, SetSettingsRequest settings) throws RippleException, IOException;

    @POST
    @Path("payments")
    public PaymentResponse pay(PaymentRequest paymentRequest) throws RippleException, IOException;


    @GET
    @Path("uuid")
    public UuidResponse generateUuid();
}
