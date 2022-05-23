package edu.microserviceslab.usagemicroservice.controller;

import edu.microserviceslab.usagemicroservice.entity.UsageStatistic;
import edu.microserviceslab.usagemicroservice.service.interfaces.UsageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController {

    private UsageService usageService;

    public UsageController(UsageService usageService) {
        this.usageService = usageService;
    }

    @ResponseBody
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public UsageStatistic addUsageStatic(@RequestBody UsageStatistic usageStatistic) {
        if(usageStatistic == null) {
            throw new IllegalArgumentException("Invalid Usage Statistic data");
        }
        if(usageStatistic.getCreatedDate() == null) {
            throw new IllegalArgumentException("Invalid Date");
        }
        if(usageStatistic.getDriverFullname() == null) {
            throw new IllegalArgumentException("Invalid driver name");
        }
        if(usageStatistic.getDriverId() < 0 || usageStatistic.getDriverId() == null) {
            throw new IllegalArgumentException("Invalid Driver ID");
        }
        if(usageStatistic.getFuelLevel() < 0 || usageStatistic.getFuelLevel() == null) {
            throw new IllegalArgumentException("Invalid Fuel Level");
        }
        if(usageStatistic.getLatitude() == null) {
            throw new IllegalArgumentException("Invalid Latitude");
        }
        if(usageStatistic.getLongitude() == null) {
            throw new IllegalArgumentException("Invalid Longitude");
        }
        if(usageStatistic.getRotationsPerMinute() < 0 || usageStatistic.getRotationsPerMinute() == null) {
            throw new IllegalArgumentException("Invalid Rotations Per Minute");
        }
        if(usageStatistic.getSpeed() < 0 || usageStatistic.getSpeed() == null) {
            throw new IllegalArgumentException("Invalid speed");
        }
        if(usageStatistic.getVehicleId() < 0 || usageStatistic.getVehicleId() == null) {
            throw new IllegalArgumentException("Invalid vehicle ID");
        }
        if(usageStatistic.getVehicleLicensePlate() == null || usageStatistic.getVehicleLicensePlate().length() != 6) {
            throw new IllegalArgumentException("Invalid license plate");
        }

        return usageService.addUsageStatistic(usageStatistic);
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<UsageStatistic> listAllUsageStatistics() {
        return usageService.getAllUsageStatistics();
    }

    @ResponseBody
    @RequestMapping("/driver/{driverId}")
    public List<UsageStatistic> listAllUsageStatisticsForDriver(@PathVariable("driverId") Long driverId) {
        return usageService.getUsageStatisticsPerDriver(driverId);
    }

    @ResponseBody
    @RequestMapping("/vehicle/{vehicleId}")
    public List<UsageStatistic> listAllUsageStatisticsForVehicle(@PathVariable("vehicleId") Long vehicleId) {
        return usageService.getUsageStatisticsPerVehicle(vehicleId);
    }
}
