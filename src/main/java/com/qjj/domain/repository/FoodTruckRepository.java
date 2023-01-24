package com.qjj.domain.repository;

import com.qjj.persistence.model.FoodTruckEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTruckRepository extends CrudRepository<FoodTruckEntity, Long> {
      @Query(value = "SELECT * FROM (SELECT *," +
              " (6373000 * acos(cos( radians(latitude) )" +
              "    * cos( radians( :latitude ) )" +
              "    * cos( radians( :longitude ) - radians(longitude) )" +
              "    + sin( radians(latitude) )" +
              "      * sin( radians( :latitude ) ) ) )  " +
              "AS distance FROM foodtruck) AS t WHERE distance <= :max_distance ORDER BY distance", nativeQuery = true)
    List<FoodTruckEntity> fetchByLocationNearBy(@Param("latitude")double latitude,
                                                @Param("longitude") double longitude,
                                                @Param("max_distance") double maxDistance);

    FoodTruckEntity findByApplicantAndAddress(String applicant, String address);
}
