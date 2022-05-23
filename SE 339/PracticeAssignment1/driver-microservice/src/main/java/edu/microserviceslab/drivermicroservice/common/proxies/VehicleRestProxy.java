package edu.microserviceslab.drivermicroservice.common.proxies;

import edu.microserviceslab.drivermicroservice.common.config.DriverConfigProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleRestProxy {

    private DriverConfigProperties driverConfigProperties;

    private final RestTemplate restTemplate;

    public VehicleRestProxy(DriverConfigProperties driverConfigProperties) {
        this.driverConfigProperties = driverConfigProperties;
        this.restTemplate = new RestTemplate();
    }

    public String getVehicleLicensePlate(Long vehicleId) {
        return restTemplate.getForObject(driverConfigProperties.getVehicleBaseUrl() + "vehicle/licensePlate/{vehicleId}", String.class, vehicleId);
    }
}
