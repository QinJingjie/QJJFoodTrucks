package com.qjj.entity;

import com.qjj.domain.FacilityType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foodtruck")
public class FoodTruckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "applicant")
    private String  applicant;

    @Column(name = "fooditems")
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "food_items", joinColumns = @JoinColumn(name = "id"))
    private List<String> foodItems;

    @Column(name = "facilitytype")
    private FacilityType facilityType;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<String> foodItems) {
        this.foodItems = foodItems;
    }

    public FacilityType getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public FoodTruckEntity() {
    }

    public FoodTruckEntity(String address, String location, String applicant, List<String> foodItems, FacilityType facilityType, double longitude, double latitude) {
        this.address = address;
        this.location = location;
        this.applicant = applicant;
        this.foodItems = foodItems;
        this.facilityType = facilityType;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
