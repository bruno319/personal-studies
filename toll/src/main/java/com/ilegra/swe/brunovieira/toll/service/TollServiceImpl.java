package com.ilegra.swe.brunovieira.toll.service;

import com.ilegra.swe.brunovieira.toll.domain.Vehicle;
import com.ilegra.swe.brunovieira.toll.domain.VehicleFactory;
import com.ilegra.swe.brunovieira.toll.dto.ChangeDto;
import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TollServiceImpl implements TollService {

    private VehicleFactory vehicleFactory;

    @Autowired
    public TollServiceImpl(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public ChangeDto pay(double payment, Vehicle vehicle) throws InvalidPaymentException {
        double change = vehicle.changeFor(payment);
        return createChangeDto(change);
    }

    @Override
    public Vehicle obtainVehicle(TransitDto transitDto) {
        return vehicleFactory.create(transitDto);
    }

    private ChangeDto createChangeDto(double change) {
        ChangeDto changeDto = new ChangeDto();
        changeDto.setChange(change);
        return changeDto;
    }

    @Override
    public Map<String, Double> getRates() {
        Map<String, Double> vehicles = new HashMap<>();

        int type = 1;
        TransitDto transitDto = new TransitDto();
        transitDto.setAxles(1);
        transitDto.setVehicleType(type);
        while (true) {
            try {
                Vehicle vehicle = vehicleFactory.create(transitDto);
                vehicles.put(vehicle.getClass().getSimpleName(), vehicle.getRate());
                type++;
                transitDto.setVehicleType(type);
            } catch (IllegalArgumentException e) {
                break;
            }
        }
        return vehicles;
    }
}
