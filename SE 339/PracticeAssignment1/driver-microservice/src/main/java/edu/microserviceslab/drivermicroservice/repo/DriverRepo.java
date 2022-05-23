package edu.microserviceslab.drivermicroservice.repo;

import edu.microserviceslab.drivermicroservice.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Long> {
}
