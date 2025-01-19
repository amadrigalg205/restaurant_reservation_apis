package com.edteam.restaurant_reservation.service;

import com.edteam.restaurant_reservation.domain.entity.Restaurant;
import com.edteam.restaurant_reservation.dto.response.RestaurantResponseDTO;
import com.edteam.restaurant_reservation.mapper.RestaurantMapper;
import com.edteam.restaurant_reservation.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Transactional
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        return restaurants.map(restaurantMapper::toResponseDto);
    }

    @Transactional
    public Page<RestaurantResponseDTO> getRestaurantByDistrictName(String districtName, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByDistrictName(districtName, pageable);
        return restaurants.map(restaurantMapper::toResponseDto);
    }


    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurantMapper.toResponseDto(restaurant);
    }
}
