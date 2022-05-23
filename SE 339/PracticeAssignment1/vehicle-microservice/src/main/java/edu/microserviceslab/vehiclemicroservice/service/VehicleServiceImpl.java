package edu.microserviceslab.vehiclemicroservice.service;

import edu.microserviceslab.vehiclemicroservice.entity.Registration;
import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import edu.microserviceslab.vehiclemicroservice.repo.RegistrationRepo;
import edu.microserviceslab.vehiclemicroservice.repo.VehicleRepo;
import edu.microserviceslab.vehiclemicroservice.service.interfaces.VehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepo vehicleRepo;
    private RegistrationRepo registrationRepo;

    public VehicleServiceImpl(VehicleRepo vehicleRepo, RegistrationRepo registrationRepo) {
        this.vehicleRepo = vehicleRepo;
        this.registrationRepo = registrationRepo;
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) { return this.vehicleRepo.save(vehicle); }

    @Override
    public Registration addRegistration(Registration registration) { return this.registrationRepo.save(registration); }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public String getVehicleLicensePlate(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(vehicleId);

        String toReturn = null;
        if (vehicle.isPresent()) {
            Registration registration = vehicle.get().getRegistration();
            if (registration != null) {
                toReturn = registration.getLicensePlate();
            }
        }

        return toReturn;
    }
}
