package edu.microserviceslab.drivermicroservice.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties(DriverConfigProperties.class)
@EnableJpaRepositories(basePackages = "edu.microserviceslab.drivermicroservice.repo")
public class DriverDatabaseConfig {
}
