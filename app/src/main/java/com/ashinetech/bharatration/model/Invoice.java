package com.ashinetech.bharatration.model;

/**
 * Created by Vignesh on 25-Jul-2015.
 */

public class Invoice {

    private String recipientName;
    private String recipientEmail;
    private String recipientPhone;
    private String name;
    private String street;
    private String postalBox;
    private String postalCode;
    private String city;
    private String countryCode;
    private String tin;

    public String getRecipientName() {
        return recipientName;
    }
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
    public String getRecipientEmail() {
        return recipientEmail;
    }
    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }
    public String getRecipientPhone() {
        return recipientPhone;
    }
    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getPostalBox() {
        return postalBox;
    }
    public void setPostalBox(String postalBox) {
        this.postalBox = postalBox;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getTin() {
        return tin;
    }
    public void setTin(String tin) {
        this.tin = tin;
    }

}

