package edu.microserviceslab.usagemicroservice.repo;

import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsageStatisticRepo extends JpaRepository<UsageStatistic, Long> {

    @Query("select u from usage_statistic u where u.driverId = :driverId")
    List<UsageStatistic> findByDriverId(@Param("driverId") Long driverId);

    @Query("select u from usage_statistic u where u.vehicleId = :vehicleId")
    List<UsageStatistic> findByVehicleId(@Param("vehicleId") Long vehicleId);
}
