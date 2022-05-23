package edu.microserviceslab.vehiclemicroservice.repo;

import edu.microserviceslab.vehiclemicroservice.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends JpaRepository<Registration, Long> {
}
