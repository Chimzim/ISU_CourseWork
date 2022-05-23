package edu.microserviceslab.usagemicroservice.common.proxies;

import edu.microserviceslab.usagemicroservice.common.config.UsageConfigProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DriverRestProxy {

    private UsageConfigProperties usageConfigProperties;

    private final RestTemplate restTemplate;

    public DriverRestProxy(UsageConfigProperties usageConfigProperties) {
        this.usageConfigProperties = usageConfigProperties;
        this.restTemplate = new RestTemplate();
    }

    public String getVehicleForDriver(Long driverId) {
        return restTemplate.getForObject(usageConfigProperties.getVehicleBaseUrl() + "driver/{driverId}/vehicleId", String.class, driverId);
    }
}
