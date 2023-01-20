package com.qjj.presentation.controller;

import com.qjj.domain.model.FoodTruck;
import com.qjj.service.FoodTruckDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodtruck")
public class FoodTruckController {
    private FoodTruckDomainService foodTruckDomainService;

    @Autowired
    public FoodTruckController(FoodTruckDomainService foodTruckDomainService) {
        this.foodTruckDomainService = foodTruckDomainService;
    }
    @GetMapping("/list")
    public HttpEntity<List<FoodTruck>> getAllFoodTrucks() {
        return new ResponseEntity<>(foodTruckDomainService.getAllFoodTrucks(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public HttpEntity<List<FoodTruck>> searchNearByFoodTrucks(@RequestParam(value = "longitude")Double longitude,
                                                             @RequestParam(value = "latitude")Double latitude,
                                                             @RequestParam(value = "maxDistance") Double maxDistance) {
        List<FoodTruck> foodTruckList = foodTruckDomainService.getNearByFoodTruck(longitude, latitude, maxDistance);
        return new ResponseEntity<>(foodTruckList, HttpStatus.OK);
    }
}
