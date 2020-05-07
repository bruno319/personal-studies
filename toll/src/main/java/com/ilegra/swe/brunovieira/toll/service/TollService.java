package com.ilegra.swe.brunovieira.toll.service;

import com.ilegra.swe.brunovieira.toll.domain.Vehicle;
import com.ilegra.swe.brunovieira.toll.dto.ChangeDto;
import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;

import java.util.Map;

public interface TollService {

    ChangeDto pay(double payment, Vehicle vehicle) throws InvalidPaymentException;
    Vehicle obtainVehicle(TransitDto transitDto);
    Map<String, Double> getRates();
}
