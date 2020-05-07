package com.ilegra.swe.brunovieira.toll.controller;

import com.ilegra.swe.brunovieira.toll.domain.Vehicle;
import com.ilegra.swe.brunovieira.toll.dto.ChangeDto;
import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import com.ilegra.swe.brunovieira.toll.service.TollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Controller
@ApplicationPath("toll")
public class TollController extends Application {

    private TollService tollService;

    @Autowired
    public TollController(TollService tollService) {
        this.tollService = tollService;
    }

    @POST
    @Path("/vehicles")
    @Produces("application/json")
    @Consumes("application/json")
    public Response payRate(TransitDto transitDto) {
        try {
            Vehicle vehicle = tollService.obtainVehicle(transitDto);
            ChangeDto changeDto = tollService.pay(transitDto.getValue(), vehicle);
            return Response.ok()
                    .entity(changeDto)
                    .build();
        } catch (InvalidPaymentException e) {
            return Response.status(Response.Status.PAYMENT_REQUIRED)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/vehicles")
    @Produces("application/json")
    public Response getRates() {
        return Response.ok(tollService.getRates()).build();
    }
}
