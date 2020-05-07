package com.ilegra.swe.brunovieira.toll.controller;

import com.ilegra.swe.brunovieira.toll.domain.Bus;
import com.ilegra.swe.brunovieira.toll.domain.Vehicle;
import com.ilegra.swe.brunovieira.toll.dto.ChangeDto;
import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import com.ilegra.swe.brunovieira.toll.service.TollService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TollControllerTest {

    @Mock
    TollService tollService;

    @InjectMocks
    TollController tollController;

    @Test
    public void shouldPayRateGivenDto() throws InvalidPaymentException {
        when(tollService.obtainVehicle(any(TransitDto.class))).thenReturn(mock(Bus.class));

        ChangeDto changeDto = new ChangeDto();
        when(tollService.pay(anyDouble(), any(Vehicle.class))).thenReturn(changeDto);

        Response response = tollController.payRate(new TransitDto());
        assertEquals(200, response.getStatus());
        assertEquals(changeDto, response.getEntity());
    }

    @Test
    public void shouldReturnBadRequestWhenVehicleTypeIsNotIdentified() {
        when(tollService.obtainVehicle(any(TransitDto.class))).thenThrow(IllegalArgumentException.class);

        Response response = tollController.payRate(new TransitDto());
        assertEquals(400, response.getStatus());
    }

    @Test
    public void shouldReturnPaymentRequiredWhenMoneyIsNotEnough() throws InvalidPaymentException {
        when(tollService.obtainVehicle(any(TransitDto.class))).thenReturn(mock(Bus.class));
        when(tollService.pay(anyDouble(), any(Vehicle.class))).thenThrow(InvalidPaymentException.class);

        Response response = tollController.payRate(new TransitDto());
        assertEquals(402, response.getStatus());
    }

    @Test
    public void shouldReturnListOfRates() {
        HashMap rates = mock(HashMap.class);
        when(tollService.getRates()).thenReturn(rates);

        Response response = tollController.getRates();

        assertEquals(200, response.getStatus());
        assertEquals(rates, response.getEntity());
    }

}
