package edu.microserviceslab.drivermicroservice.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties("driver")
@PropertySource("classpath:application.properties")
public class DriverConfigProperties {

    private String vehicleBaseUrl;

    public String getVehicleBaseUrl() {
        return vehicleBaseUrl;
    }

    public void setVehicleBaseUrl(String vehicleBaseUrl) {
        this.vehicleBaseUrl = vehicleBaseUrl;
    }
}
