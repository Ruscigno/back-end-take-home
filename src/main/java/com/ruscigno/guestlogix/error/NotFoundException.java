package com.ruscigno.guestlogix.error;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2246519313095829360L;

    public NotFoundException(String message){
        super(message);
    }
}