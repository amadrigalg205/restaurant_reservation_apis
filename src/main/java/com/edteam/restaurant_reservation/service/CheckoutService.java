package com.edteam.restaurant_reservation.service;

import com.edteam.restaurant_reservation.domain.entity.Reservation;
import com.edteam.restaurant_reservation.dto.paypal.OrderCaptureResponse;
import com.edteam.restaurant_reservation.dto.paypal.OrderResponse;
import com.edteam.restaurant_reservation.dto.response.PaypalCaptureResponse;
import com.edteam.restaurant_reservation.dto.response.PaypalOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckoutService {

    private final PaypalService paypalService;
    private final ReservationService reservationService;

    public PaypalOrderResponse createPaypalPaymentUrl(Long reservationId, String returnUrl, String cancelUrl) {

        OrderResponse orderResponse = paypalService.createOrder(
            reservationId,
            returnUrl,
            cancelUrl
        );

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(Link -> Link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(""))
                .getHref();

        return new PaypalOrderResponse(paypalUrl);
    }

    public PaypalCaptureResponse capturePaypalPayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = paypalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaypalCaptureResponse paypalCaptureResponse = new PaypalCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().getFirst().getReferenceId();
            Reservation reservation = reservationService.confirmReservationPayment(Long.parseLong(purchaseIdStr));
            paypalCaptureResponse.setReservationId(reservation.getId());
        }
        return paypalCaptureResponse;
    }
}
