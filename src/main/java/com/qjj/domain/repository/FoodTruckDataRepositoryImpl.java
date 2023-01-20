package com.qjj.domain.repository;

import com.qjj.persistence.model.FoodTruckEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FoodTruckDataRepositoryImpl implements FoodTruckDataRepository{

    private FoodTruckRepository repository;

    @Autowired
    public void setFoodTruckDataRepository(final FoodTruckRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FoodTruckEntity> refreshDataSource(List<FoodTruckEntity> entities) {
        List<FoodTruckEntity> foodTruckEntityList = new ArrayList<>();
        entities.forEach(entity -> foodTruckEntityList.add(saveFoodTruckData(entity)));
        return foodTruckEntityList;
    }

    @Override
    public FoodTruckEntity saveFoodTruckData(FoodTruckEntity foodTruckEntity) {
        FoodTruckEntity entity = repository.findByApplicantAndAddress(foodTruckEntity.getApplicant(), foodTruckEntity.getAddress());
        if(entity != null) {
            foodTruckEntity.setId(entity.getId());
        }
        repository.save(foodTruckEntity);
        return foodTruckEntity;
    }
}
