package edu.microserviceslab.vehiclemicroservice.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "licensed_to")
    private String licensedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensedTo() {
        return licensedTo;
    }

    public void setLicensedTo(String licensedTo) {
        this.licensedTo = licensedTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Registration that = (Registration) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(licensePlate, that.licensePlate)
                .append(licensedTo, that.licensedTo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(licensePlate)
                .append(licensedTo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("licensePlate", licensePlate)
                .append("licensedTo", licensedTo)
                .toString();
    }
}
