package com.edteam.restaurant_reservation.repository;

import com.edteam.restaurant_reservation.domain.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    //JPQL = Java Persistance Query Language
    @Query("SELECT r FROM Restaurant r WHERE r.district.name =:districtName")
    Page<Restaurant> findByDistrictName(@Param("districtName") String districtName, Pageable pageable);

}
