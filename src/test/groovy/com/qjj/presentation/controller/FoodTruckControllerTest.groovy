package com.qjj.presentation.controller

import com.qjj.domain.model.FacilityType
import com.qjj.domain.repository.FoodTruckRepository
import com.qjj.persistence.model.FoodTruckEntity
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Unroll

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FoodTruckControllerTest extends spock.lang.Specification {

    @Autowired
    FoodTruckController foodTruckController

    @Autowired
    FoodTruckRepository repository
    String basePath = "/foodtruck"

    MockMvc mockMvc

//    @Autowired
//    void setFoodTruckControllerMethod(FoodTruckController foodTruckController){
//        this.foodTruckController = foodTruckController
//    }
//
//    @Autowired
//    void setFoodTruckRepositoryMethod(FoodTruckRepository foodTruckRepository) {
//        this.repository = foodTruckRepository;
//    }

    void setup() {
        FoodTruckEntity foodTruckEntity1 = new FoodTruckEntity("address1",
                "location1", "applicant1",
                ["Soft Pretzels", "hot dogs", "sausage", "chips", "popcorn"],
                FacilityType.PUSH_CART, -122.38906620560476, 37.78623677446536)
        FoodTruckEntity foodTruckEntity2 = new FoodTruckEntity("address1",
                "location2", "applicant2", ["chips", "popcorn", "soda", "espresso"],
        FacilityType.PUSH_CART, -122.39092026296217, 37.78690491945698)

        FoodTruckEntity foodTruckEntity3 = new FoodTruckEntity("address1",
                "location3", "applicant3", ["chips", "popcorn", "soda", "espresso"],
                FacilityType.PUSH_CART, -122.39118192026297, 37.78814528329282)

        FoodTruckEntity foodTruckEntity4 = new FoodTruckEntity("address1",
                "location4", "applicant4", ["chips", "popcorn", "soda", "espresso"],
                FacilityType.PUSH_CART, -122.39222370638346, 37.78732241931987)

        FoodTruckEntity foodTruckEntity5 = new FoodTruckEntity("address1",
                "location5", "applicant5", ["chips", "popcorn", "soda", "espresso"],
                FacilityType.PUSH_CART, -122.39257951537353, 37.78813947887162)

        List<FoodTruckEntity> foodTruckEntityList = [foodTruckEntity1, foodTruckEntity2, foodTruckEntity3, foodTruckEntity4, foodTruckEntity5]
        foodTruckEntityList.forEach{repository.save(it)}

        mockMvc = MockMvcBuilders.standaloneSetup(foodTruckController).build()
    }

    void cleanup() {
        repository.deleteAll()
    }
    def "GetAllFoodTrucks"() {
        given:
        List<FoodTruckEntity> expectedFoodTrucks = repository.findAll()

        when:
        def response = mockMvc.perform(get(basePath + '/list' ))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$', Matchers.hasSize(expectedFoodTrucks.size())))
    }


    @Unroll("maxDistance #maxDistance")
    def "SearchNearByFoodTrucks"() {
        given:
        def latitude = 37.78623677446536
        def longitude = -122.38906620560476
        when:
        def response = mockMvc.perform(get("$basePath/search?latitude=$latitude&longitude=$longitude&maxDistance=$maxDistance").contentType(APPLICATION_JSON))

        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$', Matchers.hasSize(expectedFoodTrucks)))

        where:
        maxDistance || expectedFoodTrucks
        100         || 1
        200         || 2
        300         || 3
        400         || 5
    }
}
