package com.qjj.domain.repository;

import com.qjj.persistence.model.FoodTruckEntity;

import java.util.List;

public interface FoodTruckDataRepository {
   List<FoodTruckEntity> refreshDataSource(List<FoodTruckEntity> foodTruckEntities);
   FoodTruckEntity saveFoodTruckData(FoodTruckEntity foodTruckEntity);
}
