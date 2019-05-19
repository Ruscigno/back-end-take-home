package com.ruscigno.guestlogix.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ruscigno.guestlogix.domain.Airline;

@Component
public class AirlineRepositoryImpl{
	private List<Airline> items = new ArrayList<>();
	
	public List<Airline> findAll(){
		return items;
	}
	
	public Airline save(Airline airline) {
		if (items.stream().filter(item -> item.equals(airline)).findAny().isEmpty()) {
			items.add(airline);
		}
		return airline;
	}

	public Optional<Airline> findById(String twoDigitCode) {
		return items.stream().filter(item -> item.getTwoDigitCode().equalsIgnoreCase(twoDigitCode)).findFirst();
	}
}
