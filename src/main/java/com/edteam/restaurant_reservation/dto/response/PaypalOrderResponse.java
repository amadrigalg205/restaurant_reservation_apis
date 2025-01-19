package com.edteam.restaurant_reservation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaypalOrderResponse {
    private String paypalUrl;
}
