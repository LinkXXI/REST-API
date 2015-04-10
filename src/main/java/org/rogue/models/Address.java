package org.rogue.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Kevin on 2015-04-07.
 */
@Entity
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String line1;
    private String line2;
    private String line3;

    private String city;
    private String provinceOrState;
    private String country;
    private String postalZipCode;

    public Address() {
    }

    public Address(String line1, String line2, String line3, String city, String provinceOrState, String country, String postalZipCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.city = city;
        this.provinceOrState = provinceOrState;
        this.country = country;
        this.postalZipCode = postalZipCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceOrState() {
        return provinceOrState;
    }

    public void setProvinceOrState(String provinceOrState) {
        this.provinceOrState = provinceOrState;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalZipCode() {
        return postalZipCode;
    }

    public void setPostalZipCode(String postalZipCode) {
        this.postalZipCode = postalZipCode;
    }
}
