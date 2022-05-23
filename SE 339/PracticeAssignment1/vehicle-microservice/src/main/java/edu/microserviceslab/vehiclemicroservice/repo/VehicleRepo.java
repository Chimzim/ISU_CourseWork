package edu.microserviceslab.vehiclemicroservice.repo;

import edu.microserviceslab.vehiclemicroservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}
