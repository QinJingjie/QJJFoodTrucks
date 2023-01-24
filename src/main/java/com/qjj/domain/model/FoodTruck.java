package com.qjj.domain.model;

import java.util.List;

public class FoodTruck {
    private Location location;
    private String address;
    private String applicant;
    private List<String> foodItemsList;
    private FacilityType facilityType;

    public FoodTruck(Location location, String address, String applicant, List<String> foodItemsList, FacilityType facilityType) {
        this.location = location;
        this.address = address;
        this.applicant = applicant;
        this.foodItemsList = foodItemsList;
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
        return foodItemsList;
    }

    public void setFoodItemsList(List<String> foodItemsList) {
        this.foodItemsList = foodItemsList;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

}
