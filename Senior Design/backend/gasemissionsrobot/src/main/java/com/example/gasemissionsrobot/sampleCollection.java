package com.example.gasemissionsrobot;

import javax.persistence.*;

/**
 *
 * @author chimz
 *Goes to the table named "sampleSite"
 */
@Entity
@Table(name="sampleCollection")
public class sampleCollection {

    /**
     * The month of the sample collected sample
     */
    @Column
    int Month;
    /**
     * The day of the sample collected sample
     */
    @Column
    int Day;
    /**
     * The year of the sample collected sample
     */
    @Column
    int Year;
    /**
     * ID of the the sample site
     */
    @Column
    int sampleID;
    /**
     * The CO2 sensor1 of the sample collected sample
     */
    @Column
    double sensor1_CO2;
    /**
     * The CO2 sensor2 of the sample collected sample
     */
    @Column
    double sensor2_CO2;
    /**
     * The CO2 sensor3 of the sample collected sample
     */
    @Column
    double sensor3_CO2;
    /**
     * The CO2 sensor4 of the sample collected sample
     */
    @Column
    double sensor4_CO2;
    /**
     * The CO2 sensor5 of the sample collected sample
     */
    @Column
    double sensor5_CO2;
    /**
     * The longitude position of the sample site
     */
    @Column
    double longitude;
    /**
     * The  position of the sample site
     */
    @Column
    double latitude;


    /**
     * Sets the month for the collected sample
     *
     * @param Month - number 1-12 for the corresponding month
     */
    public void setMonth(int Month) {
        this.Month = Month;
    }

    /**
     * Returns the month number for the collected sample
     *
     * @return Month - number 1-12 for the corresponding month
     */
    public int getMonth() {
        return Month;
    }

    /**
     * Sets the day of the month for the collected sample
     *
     * @param Day - number 1-31 for the corresponding day of the month
     */
    public void setDay(int Day) {
        this.Day = Day;
    }

    /**
     * Returns the day of the month number for the collected sample
     *
     * @return Month - number 1-31 for the corresponding day of the month
     */
    public int getDay() {
        return Day;
    }

    /**
     * Sets the year for the collected sample
     *
     * @param Year - the current year as xxxx
     */
    public void setYear(int Year) {
        this.Year = Year;
    }

    /**
     * Returns the yearfor the collected sample
     *
     * @return Year - the current year as xxxx
     */
    public int getYear() {
        return Year;
    }

    /**
     * Sets the sample ID for the collected sample
     *
     * @param sampleID
     */
    public void setSampleID(int sampleID) {
        this.sampleID = sampleID;
    }

    /**
     * Returns the sample ID for the collected sample
     *
     * @return sampleID - the ID of the collected sample
     */
    public int getSampleID() {
        return sampleID;
    }

    /**
     * Sets the CO2 readings from sensor 1
     *
     * @param sensor1_CO2 - collected CO2 readings from sensor 1 units (ppm)
     */
    public void setSensor1_CO2(double sensor1_CO2) {
        this.sensor1_CO2 = sensor1_CO2;
    }

    /**
     * Returns the CO2 readings from sensor 1
     *
     * @return sensor1_CO2 - collected CO2 readings from sensor 1 units (ppm
     */
    public double getSensor1_CO2() {
        return sensor1_CO2;
    }

    /**
     * Sets the CO2 readings from sensor 2
     *
     * @param sensor2_CO2 - collected CO2 readings from sensor 2 units (ppm)
     */
    public void setSensor2_CO2(double sensor2_CO2) {
        this.sensor2_CO2 = sensor2_CO2;
    }

    /**
     * Returns the CO2 readings from sensor 2
     *
     * @return sensor2_CO2 - collected CO2 readings from sensor 2 units (ppm)
     */
    public double getSensor2_CO2() {
        return sensor2_CO2;
    }

    /**
     * Sets the CO2 readings from sensor 3
     *
     * @param sensor3_CO2 - collected CO2 readings from sensor 3 units (ppm)
     */
    public void setSensor3_CO2(double sensor3_CO2) {
        this.sensor3_CO2 = sensor3_CO2;
    }

    /**
     * Returns the CO2 readings from sensor 3
     *
     * @return sensor3_CO2 - collected CO2 readings from sensor 3 units (ppm)
     */
    public double getSensor3_CO2() {
        return sensor3_CO2;
    }

    /**
     * Sets the CO2 readings from sensor 4
     *
     * @param sensor4_CO2 - collected CO2 readings from sensor 4 units (ppm)
     */
    public void setSensor4_CO2(double sensor4_CO2) {
        this.sensor4_CO2 = sensor4_CO2;
    }

    /**
     * Returns the CO2 readings from sensor 4
     *
     * @return sensor4_CO2 - collected CO2 readings from sensor 4 units (ppm)
     */
    public double getSensor4_CO2() {
        return sensor4_CO2;
    }

    /**
     * Sets the CO2 readings from sensor 5
     *
     * @param sensor5_CO2 - collected CO2 readings from sensor 5 units (ppm)
     */
    public void setSensor5_CO2(double sensor5_CO2) {
        this.sensor5_CO2 = sensor5_CO2;
    }

    /**
     * Returns the CO2 readings from sensor 5
     *
     * @return sensor5_CO2 - collected CO2 readings from sensor 5 units (ppm)
     */
    public double getSensor5_CO2() {
        return sensor5_CO2;
    }
    /**
     * Sets the longitude position of the collected sample
     *
     * @param longitude - longitude position of the collected sample
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Returns the longitude position of the collected sample
     *
     * @return longitude - longitude position of the collected sample
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the latitude position of the collected sample
     *
     * @param latitude - latitude position of the collected sample
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the latitude position of the collected sample
     *
     * @return latitude - latitude position of the collected sample
     */
    public double getLatitude() {
        return latitude;
    }
}
