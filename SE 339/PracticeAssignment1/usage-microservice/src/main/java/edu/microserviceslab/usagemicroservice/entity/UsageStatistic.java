package edu.microserviceslab.usagemicroservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "usage_statistic")
public class UsageStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Date and Time variable for when the usage statistic was added
     */
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    /**
     * Calculated as Miles Per Hour
     */
    @Column(name = "speed")
    private Double speed;

    /**
     * Calculated as percentage from full
     */
    @Column(name = "fuel_level")
    private Double fuelLevel;

    @Column(name = "rotations_per_minute")
    private Long rotationsPerMinute;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "driver_fullname")
    private String driverFullname;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_license_plate")
    private String vehicleLicensePlate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(Double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public Long getRotationsPerMinute() {
        return rotationsPerMinute;
    }

    public void setRotationsPerMinute(Long rotationsPerMinute) {
        this.rotationsPerMinute = rotationsPerMinute;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverFullname() {
        return driverFullname;
    }

    public void setDriverFullname(String driverFullname) {
        this.driverFullname = driverFullname;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleLicensePlate() {
        return vehicleLicensePlate;
    }

    public void setVehicleLicensePlate(String vehicleLicensePlate) {
        this.vehicleLicensePlate = vehicleLicensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UsageStatistic that = (UsageStatistic) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(createdDate, that.createdDate)
                .append(speed, that.speed)
                .append(fuelLevel, that.fuelLevel)
                .append(rotationsPerMinute, that.rotationsPerMinute)
                .append(latitude, that.latitude)
                .append(longitude, that.longitude)
                .append(driverId, that.driverId)
                .append(driverFullname, that.driverFullname)
                .append(vehicleId, that.vehicleId)
                .append(vehicleLicensePlate, that.vehicleLicensePlate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(createdDate)
                .append(speed)
                .append(fuelLevel)
                .append(rotationsPerMinute)
                .append(latitude)
                .append(longitude)
                .append(driverId)
                .append(driverFullname)
                .append(vehicleId)
                .append(vehicleLicensePlate)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("createdDate", createdDate)
                .append("speed", speed)
                .append("fuelLevel", fuelLevel)
                .append("rotationsPerMinute", rotationsPerMinute)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("driverId", driverId)
                .append("driverFullname", driverFullname)
                .append("vehicleId", vehicleId)
                .append("vehicleLicensePlate", vehicleLicensePlate)
                .toString();
    }
}
