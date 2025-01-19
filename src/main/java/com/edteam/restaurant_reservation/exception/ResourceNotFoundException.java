package com.edteam.restaurant_reservation.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
