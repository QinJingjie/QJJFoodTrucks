package com.qjj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruckDto {
    private String locationdescription;
    private String address;
    private String applicant;
    private String fooditems;
    private String facilitytype;
    private double latitude;
    private double longitude;

    public FoodTruckDto() {
    }

    public FoodTruckDto(String locationdescription, String address, String applicant, String fooditems, String facilitytype, double latitude, double longitude) {
        this.locationdescription = locationdescription;
        this.address = address;
        this.applicant = applicant;
        this.fooditems = fooditems;
        this.facilitytype = facilitytype;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocationdescription() {
        return locationdescription;
    }

    public void setLocationdescription(String locationdescription) {
        this.locationdescription = locationdescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFooditems() {
        return fooditems;
    }

    public void setFooditems(String fooditems) {
        this.fooditems = fooditems;
    }

    public String getFacilitytype() {
        return facilitytype;
    }

    public void setFacilitytype(String facilitytype) {
        this.facilitytype = facilitytype;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
