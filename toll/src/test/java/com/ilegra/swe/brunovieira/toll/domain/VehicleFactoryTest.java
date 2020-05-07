package com.ilegra.swe.brunovieira.toll.domain;

import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class VehicleFactoryTest {

    VehicleFactory vehicleFactory;
    TransitDto transitDto;

    @Before
    public void setup() {
        vehicleFactory = new VehicleFactory();
        transitDto = new TransitDto();
    }

    @Test
    public void shouldCreateBike() {
        transitDto.setVehicleType(1);
        Vehicle vehicle = vehicleFactory.create(transitDto);
        assertTrue(vehicle instanceof Bike);
    }

    @Test
    public void shouldCreateMotorcycle() {
        transitDto.setVehicleType(2);
        Vehicle vehicle = vehicleFactory.create(transitDto);
        assertTrue(vehicle instanceof Motorcycle);
    }

    @Test
    public void shouldCreateBeetle() {
        transitDto.setVehicleType(3);
        Vehicle vehicle = vehicleFactory.create(transitDto);
        assertTrue(vehicle instanceof Beetle);
    }

    @Test
    public void shouldCreateBus() {
        transitDto.setVehicleType(4);
        Vehicle vehicle = vehicleFactory.create(transitDto);
        assertTrue(vehicle instanceof Bus);
    }

    @Test
    public void shouldCreateTruck() {
        transitDto.setVehicleType(5);
        Vehicle vehicle = vehicleFactory.create(transitDto);
        assertTrue(vehicle instanceof Truck);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfVehicleTypeIsNotIdentified() {
        transitDto.setVehicleType(2000);
        vehicleFactory.create(transitDto);
    }
}
