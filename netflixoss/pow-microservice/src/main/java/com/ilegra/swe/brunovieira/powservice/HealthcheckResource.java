package com.ilegra.swe.brunovieira.powservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.netty.handler.codec.http.HttpResponseStatus;
import netflix.karyon.health.HealthCheckHandler;

@Path("/healthcheck")
public class HealthcheckResource implements HealthCheckHandler {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthcheck() {
        return Response.status(getStatus()).build();
    }

    @Override
    public int getStatus() {
        return Response.Status.OK.getStatusCode();
    }

}