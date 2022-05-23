package edu.microserviceslab.usagemicroservice.common.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties(UsageConfigProperties.class)
@EnableJpaRepositories(basePackages = "edu.microserviceslab.usagemicroservice.repo")
public class UsageDatabaseConfig {
}
