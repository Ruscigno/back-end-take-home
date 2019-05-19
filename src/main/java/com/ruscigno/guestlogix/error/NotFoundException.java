package com.ruscigno.guestlogix.error;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2246519313095829360L;
    private List<String> errors = new ArrayList<>();
    
	public NotFoundException(String message){
        super(message);
    }
    
    public NotFoundException(String message, List<String> errors){
        super(message);
        this.errors = errors;
    }
}