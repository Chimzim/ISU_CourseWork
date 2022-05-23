package edu.microserviceslab.vehiclemicroservice.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "edu.microserviceslab.vehiclemicroservice.repo")
public class VehicleDatabaseConfig {
}
