package com.edteam.restaurant_reservation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequestDTO {
    private Long id;
    @NotNull(message = "Restaurant ID is mandatory")
    private Long restaurantId;
    @NotNull(message = "Reservation date is mandatory")
    private LocalDateTime reservationDate;
    @Positive(message = "Number of people must be positive")
    private int numberOfPeople;
    private String additionalInfo;
}
