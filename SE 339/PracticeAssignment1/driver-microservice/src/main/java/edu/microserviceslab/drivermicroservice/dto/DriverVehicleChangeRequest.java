package edu.microserviceslab.drivermicroservice.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class DriverVehicleChangeRequest {

    private Long driverId;
    private Long vehicleId;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DriverVehicleChangeRequest that = (DriverVehicleChangeRequest) o;

        return new EqualsBuilder()
                .append(driverId, that.driverId)
                .append(vehicleId, that.vehicleId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(driverId)
                .append(vehicleId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("driverId", driverId)
                .append("vehicleId", vehicleId)
                .toString();
    }
}
