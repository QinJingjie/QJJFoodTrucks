package com.qjj.repository;

import com.qjj.entity.FoodTruckEntity;

import java.util.List;

public interface FoodTruckDataRepository {
   List<FoodTruckEntity> refreshDataSource(List<FoodTruckEntity> foodTruckEntities);
   FoodTruckEntity saveFoodTruckData(FoodTruckEntity foodTruckEntity);
}
