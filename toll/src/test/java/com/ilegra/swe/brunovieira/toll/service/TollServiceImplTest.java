package com.ilegra.swe.brunovieira.toll.service;

import com.ilegra.swe.brunovieira.toll.domain.Bike;
import com.ilegra.swe.brunovieira.toll.domain.Bus;
import com.ilegra.swe.brunovieira.toll.domain.Vehicle;
import com.ilegra.swe.brunovieira.toll.domain.VehicleFactory;
import com.ilegra.swe.brunovieira.toll.dto.ChangeDto;
import com.ilegra.swe.brunovieira.toll.dto.TransitDto;
import com.ilegra.swe.brunovieira.toll.exception.InvalidPaymentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TollServiceImplTest {

    @Mock
    VehicleFactory vehicleFactory;

    @InjectMocks
    TollServiceImpl tollService;

    @Test
    public void shouldReturnVehicleRates() {
        Bike bike = mock(Bike.class);
        when(bike.getRate()).thenReturn(0.49);

        Bus bus = mock(Bus.class);
        when(bus.getRate()).thenReturn(1.59);

        when(vehicleFactory.create(any(TransitDto.class)))
                .thenReturn(bike, bus)
                .thenThrow(IllegalArgumentException.class);

        Map<String, Double> rates = tollService.getRates();

        assertEquals(2, rates.size());
        assertEquals(Double.valueOf(0.49), rates.get(bike.getClass().getSimpleName()));
        assertEquals(Double.valueOf(1.59), rates.get(bus.getClass().getSimpleName()));
        verify(vehicleFactory, times(3)).create(any(TransitDto.class));
    }

    @Test
    public void shouldObtainVehicleGivenDto() {
        Vehicle mockedVehicle = mock(Bus.class);
        when(vehicleFactory.create(any(TransitDto.class))).thenReturn(mockedVehicle);

        Vehicle vehicle = tollService.obtainVehicle(new TransitDto());
        assertEquals(mockedVehicle, vehicle);
    }

    @Test
    public void shouldDoPayment() throws InvalidPaymentException {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.changeFor(anyDouble())).thenReturn(2.00);

        ChangeDto change = tollService.pay(5.00, vehicle);
        assertEquals(2.00, change.getChange(), 0);
    }

}
