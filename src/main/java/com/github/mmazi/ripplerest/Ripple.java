package com.github.mmazi.ripplerest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Ripple {

    @GET
    @Path("accounts/{address}/balances")
    public BalancesResponse getBalances(@PathParam("address") String address);





    @GET
    @Path("uuid")
    public UuidResponse generateUuid();
}
