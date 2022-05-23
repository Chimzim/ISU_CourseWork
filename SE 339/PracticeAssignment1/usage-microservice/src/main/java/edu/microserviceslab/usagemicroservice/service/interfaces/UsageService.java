package edu.microserviceslab.usagemicroservice.service.interfaces;

import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;

import java.util.List;

public interface UsageService {


    UsageStatistic addUsageStatistic(UsageStatistic usageStatistic);

    List<UsageStatistic> getAllUsageStatistics();

    List<UsageStatistic> getUsageStatisticsPerDriver(Long driverId);

    List<UsageStatistic> getUsageStatisticsPerVehicle(Long vehicleId);
}
