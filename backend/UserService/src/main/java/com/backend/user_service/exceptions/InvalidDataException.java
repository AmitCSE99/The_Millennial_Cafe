package com.backend.user_service.exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(){
        super("Invalid Data");
    }

    public InvalidDataException(String s){
        super(s);
    }
}
