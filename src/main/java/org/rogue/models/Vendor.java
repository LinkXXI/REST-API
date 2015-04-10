package org.rogue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-09.
 */

@Entity
public class Vendor implements Serializable {
    @Id
    @GeneratedValue
    private int id;

    private String contactPerson;
    private String contactNumber;

    @ManyToOne
    private Address address;

    public Vendor() {
    }

    public Vendor(String contactPerson, String contactNumber, Address address) {
        this.contactPerson = contactPerson;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
