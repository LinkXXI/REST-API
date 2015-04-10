package org.rogue.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-09.
 */

@Entity
public class Venue implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String name;
    private String description;

    private boolean parkingAvailiable;
    private boolean publicTransitAvailiable;

    @OneToOne
    private Address address;

    public Venue() {
    }

    public Venue(String name, String description, boolean parkingAvailiable, boolean publicTransitAvailiable, Address address) {
        this.name = name;
        this.description = description;
        this.parkingAvailiable = parkingAvailiable;
        this.publicTransitAvailiable = publicTransitAvailiable;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isParkingAvailiable() {
        return parkingAvailiable;
    }

    public void setParkingAvailiable(boolean parkingAvailiable) {
        this.parkingAvailiable = parkingAvailiable;
    }

    public boolean isPublicTransitAvailiable() {
        return publicTransitAvailiable;
    }

    public void setPublicTransitAvailiable(boolean publicTransitAvailiable) {
        this.publicTransitAvailiable = publicTransitAvailiable;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
