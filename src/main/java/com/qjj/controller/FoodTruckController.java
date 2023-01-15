package com.qjj.controller;

import com.qjj.service.FoodTruckDataHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodtruck")
public class FoodTruckController {
    private FoodTruckDataHandlerService foodTruckDataHandlerService;

    @Autowired
    public FoodTruckController(FoodTruckDataHandlerService foodTruckDataHandlerService) {
        this.foodTruckDataHandlerService = foodTruckDataHandlerService;
    }
    @GetMapping("/list")
    public String getAllFoodTrucks() {
        return "hello";
    }

}
