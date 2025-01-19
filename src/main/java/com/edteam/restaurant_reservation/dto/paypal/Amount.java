package com.edteam.restaurant_reservation.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Amount {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}
