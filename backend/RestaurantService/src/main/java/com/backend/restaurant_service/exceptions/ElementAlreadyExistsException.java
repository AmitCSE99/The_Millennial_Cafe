package com.backend.restaurant_service.exceptions;

public class ElementAlreadyExistsException extends RuntimeException{

    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
