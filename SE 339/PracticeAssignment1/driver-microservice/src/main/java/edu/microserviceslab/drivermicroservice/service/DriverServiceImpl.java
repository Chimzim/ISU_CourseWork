package edu.microserviceslab.drivermicroservice.service;

import edu.microserviceslab.drivermicroservice.dto.DriverVehicleChangeRequest;
import edu.microserviceslab.drivermicroservice.entity.Driver;
import edu.microserviceslab.drivermicroservice.repo.DriverRepo;
import edu.microserviceslab.drivermicroservice.service.interfaces.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    private DriverRepo driverRepo;

    public DriverServiceImpl(DriverRepo driverRepo) {
        this.driverRepo = driverRepo;
    }

    @Override
    public Driver addDriver(Driver driver) {
        return driverRepo.save(driver);
    }

    @Override
    public Driver changeVehicle(DriverVehicleChangeRequest changeRequest) {
        Optional<Driver> driverSearchResult = driverRepo.findById(changeRequest.getDriverId());
        Driver toReturn = null;

        if (driverSearchResult.isPresent()) {
            toReturn = driverSearchResult.get();
            toReturn.setVehicleId(changeRequest.getVehicleId());
            toReturn = driverRepo.save(toReturn);
        }

        return toReturn;
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverRepo.findAll();
    }

    @Override
    public Driver getDriverById(Long driverId) {
        Optional<Driver> driverSearchResult = driverRepo.findById(driverId);

        Driver toReturn = null;
        if (driverSearchResult.isPresent()) {
            toReturn = driverSearchResult.get();
        }

        return toReturn;
    }
}
