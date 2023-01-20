package com.qjj.domain;

import java.util.List;

public class FoodTruck {
    private Location location;
    private String address;
    private String applicant;
    private List<String> FoodItemsList;
    private FacilityType facilityType;

    public FoodTruck(Location location, String address, String applicant, List<String> foodItemsList, FacilityType facilityType) {
        this.location = location;
        this.address = address;
        this.applicant = applicant;
        FoodItemsList = foodItemsList;
        this.facilityType = facilityType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public List<String> getFoodItemsList() {
        return FoodItemsList;
    }

    public void setFoodItemsList(List<String> foodItemsList) {
        FoodItemsList = foodItemsList;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

}
