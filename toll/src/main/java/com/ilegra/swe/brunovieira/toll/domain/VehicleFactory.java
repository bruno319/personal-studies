package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.dto.TransitDto;

public class VehicleFactory {

    private final int BIKE = 1;
    private final int MOTORCYCLE = 2;
    private final int BEETLE = 3;
    private final int BUS = 4;
    private final int TRUCK = 5;

    public Vehicle create(TransitDto transitDto) {
        switch (transitDto.getVehicleType()) {
            case BIKE:
                return new Bike();
            case MOTORCYCLE:
                return new Motorcycle();
            case BEETLE:
                return new Beetle();
            case BUS:
                return new Bus();
            case TRUCK:
                return new Truck(transitDto.getAxles());
            default:
                throw new IllegalArgumentException("Error on creating vehicle. Invalid Type.");
        }
    }
}
