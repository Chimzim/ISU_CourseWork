package edu.microserviceslab.vehiclemicroservice.service.interfaces;

import edu.microserviceslab.vehiclemicroservice.entity.Registration;
import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    Vehicle addVehicle(Vehicle vehicle);

    Registration addRegistration(Registration registration);

    List<Vehicle> getAllVehicles();

    String getVehicleLicensePlate(Long vehicleId);
}
