package com.qjj.transport;

import com.qjj.domain.model.FacilityType;
import com.qjj.domain.model.FoodTruck;
import com.qjj.domain.model.Location;
import com.qjj.service.FoodTruckDataService;
import com.qjj.transport.model.FoodTruckDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FoodTruckFetchDataScheduler {
    private static Logger logger = LoggerFactory.getLogger(FoodTruckFetchDataScheduler.class);
    private FoodTruckDataService foodTruckDataService;
    private RestTemplate restTemplate;
    @Value("${foodtruck.data.url}")
    private String url;
    @Value("${scheduler.foodtruck.enable}")
    private boolean enabled;

    @Autowired
    public FoodTruckFetchDataScheduler(RestTemplate restTemplate,
                                       FoodTruckDataService foodTruckDataService) {
        this.restTemplate = restTemplate;
        this.foodTruckDataService = foodTruckDataService;
    }

    @Scheduled(initialDelay = 0, fixedDelay = 60 * 1000)
    public void fetchDataScheduler() {
        if(enabled) {
            List<FoodTruck> foodTruckList = fetchFoodTruckData();
            foodTruckDataService.refreshFoodTruckData(foodTruckList);
        }
    }

    public List<FoodTruck> fetchFoodTruckData() {
        List<FoodTruck> foodTruckList = new ArrayList<>();
        logger.info("start fetching data from {}", url);
        try {
            FoodTruckDto[] foodTruckDtos = restTemplate.getForObject(url, FoodTruckDto[].class);
            if(foodTruckDtos != null) {
                foodTruckList = Arrays.asList(foodTruckDtos).stream().map(dto ->
                        convertDtoToFoodTruck(dto)).collect(Collectors.toList());
                logger.info("fetched {} data items", foodTruckDtos.length);
            }
        } catch (RestClientException e){
            logger.error("fetching data from {} failed, {}", url, e);
        }
        return foodTruckList;
    }


    public FoodTruck convertDtoToFoodTruck(FoodTruckDto foodTruckDto) {
        return new FoodTruck(new Location(foodTruckDto.getLocationdescription(),
                                          foodTruckDto.getLatitude(),
                                          foodTruckDto.getLongitude()),
                             foodTruckDto.getAddress(),
                             foodTruckDto.getApplicant(),
                             convertFoodItems(foodTruckDto),
                             convertFacilityType(foodTruckDto));
    }



    public List<String> convertFoodItems(FoodTruckDto foodTruckDto) {
        if(foodTruckDto.getFooditems() != null && !foodTruckDto.getFooditems().isEmpty()) {
            return Arrays.stream(foodTruckDto.getFooditems().split(":")).map(String::trim).collect(Collectors.toList());
        } else{
            return Collections.emptyList();
        }
    }

    public FacilityType convertFacilityType(FoodTruckDto foodTruckDto) {
        if(foodTruckDto.getFacilitytype() != null && !foodTruckDto.getFacilitytype().isEmpty()) {
            return FacilityType.get(foodTruckDto.getFacilitytype());
        }else{
            return FacilityType.NONE;
        }
    }
}
