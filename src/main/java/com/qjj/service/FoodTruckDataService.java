package com.qjj.service;

import com.qjj.domain.model.FoodTruck;
import com.qjj.domain.repository.FoodTruckDataRepository;
import com.qjj.persistence.model.FoodTruckEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodTruckDataService {

    private FoodTruckDataRepository foodTruckDataRepository;

    @Autowired
    public FoodTruckDataService(FoodTruckDataRepository foodTruckDataRepository) {
        this.foodTruckDataRepository = foodTruckDataRepository;
    }

    public List<FoodTruckEntity> refreshFoodTruckData(List<FoodTruck> foodTruckList){
        List<FoodTruckEntity> entityList = foodTruckList.stream().map(
                domain -> convertFoodTruckToEntity(domain)).collect(Collectors.toList());
        return foodTruckDataRepository.refreshDataSource(entityList);
    }

    public FoodTruckEntity convertFoodTruckToEntity(FoodTruck foodTruck) {
        return new FoodTruckEntity(foodTruck.getAddress(),
                                   foodTruck.getLocation().getLocation(),
                                   foodTruck.getApplicant(),
                                   foodTruck.getFoodItemsList(),
                                   foodTruck.getFacilityType(),
                                   foodTruck.getLocation().getLongitude(),
                                   foodTruck.getLocation().getLatitude());
    }
}
