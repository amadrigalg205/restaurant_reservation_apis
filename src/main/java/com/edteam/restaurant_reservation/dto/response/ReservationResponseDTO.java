package com.edteam.restaurant_reservation.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservationResponseDTO {
    private Long id;
    private String restaurantName;
    private LocalDateTime reservationDate;
    private int numberOfPeople;
    private String status;
    private String additionalInfo;
    private double totalAmount;
}
