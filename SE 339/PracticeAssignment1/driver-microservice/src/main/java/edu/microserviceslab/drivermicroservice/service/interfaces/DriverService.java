package edu.microserviceslab.drivermicroservice.service.interfaces;

import edu.microserviceslab.drivermicroservice.dto.DriverVehicleChangeRequest;
import edu.microserviceslab.drivermicroservice.entity.Driver;

import java.util.List;

public interface DriverService {

    Driver addDriver(Driver driver);

    Driver changeVehicle(DriverVehicleChangeRequest changeRequest);

    List<Driver> getAllDrivers();

    Driver getDriverById(Long driverId);
}
