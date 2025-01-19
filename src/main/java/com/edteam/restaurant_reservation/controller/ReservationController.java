package com.edteam.restaurant_reservation.controller;

import com.edteam.restaurant_reservation.dto.request.ReservationRequestDTO;
import com.edteam.restaurant_reservation.dto.response.ReservationResponseDTO;
import com.edteam.restaurant_reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody @Validated ReservationRequestDTO reservationRequestDTO) {

        ReservationResponseDTO reservationResponseDTO = reservationService.createReservation(reservationRequestDTO);

        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/my-reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getMyReservations() {
        List<ReservationResponseDTO> reservations = reservationService.getReservationsByClientId();

        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        ReservationResponseDTO reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationResponse);
    }
}
