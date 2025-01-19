package com.edteam.restaurant_reservation.service;

import com.edteam.restaurant_reservation.domain.entity.District;
import com.edteam.restaurant_reservation.dto.response.DistrictResponseDTO;
import com.edteam.restaurant_reservation.mapper.DistrictMapper;
import com.edteam.restaurant_reservation.repository.DistrictRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DistrictService {

    private  final DistrictRepository districtRepository;
    private  final DistrictMapper districtMapper;

    @Transactional
    public List<DistrictResponseDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districtMapper.toResponseDtoList(districts);
    }
}
