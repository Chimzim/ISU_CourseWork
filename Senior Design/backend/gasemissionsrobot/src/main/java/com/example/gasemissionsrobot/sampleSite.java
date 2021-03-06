package com.example.gasemissionsrobot;

import javax.persistence.*;

/**
 *
 * @author chimz
 *Goes to the table named "sampleSite"
 */
@Entity
@Table(name="sampleSite")
public class sampleSite {
    /**
     * Unquie identifier for each sample site
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    /**
     * The title of the sample site
     */
    @Column
    String title;
    /**
     * The latitude position of the sample site
     */
    @Column
    double latitude;
    /**
     * The longitude position of the sample site
     */
    @Column
    double longitude;


    /**
     * Sets the Id of the marker
     * @param id - unique identifier for the marker
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the Id of the marker
     * @return Id - unique identifier for the marker
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the title of the marker
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the title of the marker
     * @return Title - the title of the marker
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the latitude position of the marker
     * @param latitude - latitude position of marker
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the latitude position of the marker
     * @return latitude - latitude position of marker
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the longitude position of the marker
     * @param longitude - longitude position of the marker
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    /**
     * Returns the longitude position of the marker
     * @return longitude - longitude position of the marker
     */
    public double getLongitude() {
        return longitude;
    }
}

