package com.qjj.service;

import com.qjj.domain.model.FoodTruck;
import com.qjj.domain.model.Location;
import com.qjj.domain.repository.FoodTruckRepository;
import com.qjj.persistence.model.FoodTruckEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodTruckDomainService {
    private static Logger logger = LoggerFactory.getLogger(FoodTruckDomainService.class);

    private FoodTruckRepository foodTruckRepository;

    @Autowired
    public FoodTruckDomainService(FoodTruckRepository foodTruckRepository) {
        this.foodTruckRepository = foodTruckRepository;
    }

    public List<FoodTruck> getNearByFoodTruck(Double longitude, Double latitude, double distance) {
        List<FoodTruckEntity> foodTruckEntityList = foodTruckRepository.fetchByLocationNearBy(latitude, longitude, distance);
        return foodTruckEntityList.stream().map(this::convertEntityToFoodTruck).collect(Collectors.toList());
    }

    public List<FoodTruck> getAllFoodTrucks(){
        Iterable<FoodTruckEntity> foodTruckEntityList = foodTruckRepository.findAll();
        List<FoodTruck> foodTruckList = new ArrayList<>();
        foodTruckEntityList.forEach(entity -> foodTruckList.add(convertEntityToFoodTruck(entity)));
        return foodTruckList;
    }

    public FoodTruck convertEntityToFoodTruck(FoodTruckEntity foodTruckEntity) {
        return new FoodTruck(new Location(foodTruckEntity.getLocation(),
                                          foodTruckEntity.getLatitude(),
                                          foodTruckEntity.getLongitude()),
                             foodTruckEntity.getAddress(),
                             foodTruckEntity.getApplicant(),
                             foodTruckEntity.getFoodItems(),
                             foodTruckEntity.getFacilityType());
    }


}
