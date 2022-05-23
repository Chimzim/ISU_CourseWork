package edu.microserviceslab.usagemicroservice.common.proxies;

import edu.microserviceslab.usagemicroservice.common.config.UsageConfigProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleRestProxy {

    private UsageConfigProperties usageConfigProperties;

    private final RestTemplate restTemplate;

    public VehicleRestProxy(UsageConfigProperties usageConfigProperties) {
        this.usageConfigProperties = usageConfigProperties;
        this.restTemplate = new RestTemplate();
    }

    public String getVehicleLicensePlate(Long vehicleId) {
        return restTemplate.getForObject(usageConfigProperties.getVehicleBaseUrl() + "vehicle/licensePlate/{vehicleId}", String.class, vehicleId);
    }
}
