package com.ampota.shared.dto;

import com.google.common.base.Joiner;

public class AddressInfo {

    private boolean primary;
    private String firstName;
    private String lastName;
    private String line1;
    private String line2;
    private String city;
    private String country;
    private String postalCode;

    public String getLine() {
        return Joiner.on(", ").skipNulls().join(firstName + " " + lastName, line1, line2, city, country, postalCode);
    }
    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
