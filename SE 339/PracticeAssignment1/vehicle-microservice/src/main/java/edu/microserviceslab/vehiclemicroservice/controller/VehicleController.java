package edu.microserviceslab.vehiclemicroservice.controller;

import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import edu.microserviceslab.vehiclemicroservice.service.interfaces.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        if(vehicle == null) {
            throw new IllegalArgumentException("Vehicle can not be NULL");
        }
        if(vehicle.getMake() == null) {
            System.out.println(vehicle.getMake());
            throw new IllegalArgumentException("Invalid vehicle make");
        }
        if(vehicle.getModel() == null) {
            throw new IllegalArgumentException("Invalid vehicle model");
        }
        if(vehicle.getModelYear() < 0 || vehicle.getModelYear() == null) {
            throw new IllegalArgumentException("Vehicle model year must be a non negative year");
        }
        if(vehicle.getRegistration() == null) {
            throw new IllegalArgumentException("Vehicle needs registration information");
        }
        if(vehicle.getRegistration().getLicensePlate() == null) {
            throw new IllegalArgumentException("Vehicle license plate can not be empty");
        }
        if(vehicle.getRegistration().getLicensePlate().length() > 6 || vehicle.getRegistration().getLicensePlate().length() < 6) {
            throw new IllegalArgumentException("Invalid license plate");
        }
        if(vehicle.getRegistration().getLicensedTo() == null) {
            throw new IllegalArgumentException("Vehicle must be registered to someone");
        }
       vehicleService.addRegistration(vehicle.getRegistration());

        return vehicleService.addVehicle(vehicle);
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<Vehicle> listAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @ResponseBody
    @RequestMapping("/licensePlate/{vehicleId}")
    public String getVehicleLicensePlate(@PathVariable("vehicleId") Long vehicleId) {
        return vehicleService.getVehicleLicensePlate(vehicleId);
    }
}
