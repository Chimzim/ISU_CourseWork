package edu.microserviceslab.usagemicroservice.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties("usage")
@PropertySource("classpath:application.properties")
public class UsageConfigProperties {

    private String vehicleBaseUrl;

    private String driverBaseUrl;

    public String getVehicleBaseUrl() {
        return vehicleBaseUrl;
    }

    public void setVehicleBaseUrl(String vehicleBaseUrl) {
        this.vehicleBaseUrl = vehicleBaseUrl;
    }

    public String getDriverBaseUrl() {
        return driverBaseUrl;
    }

    public void setDriverBaseUrl(String driverBaseUrl) {
        this.driverBaseUrl = driverBaseUrl;
    }
}
