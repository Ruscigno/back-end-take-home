package com.ruscigno.guestlogix.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ruscigno.guestlogix.domain.Airport;

@Component
public class AirportRepositoryImpl {
	private List<Airport> items = new ArrayList<>();
	
	public List<Airport> findAll(){
		return items;
	}
	
	public Airport save(Airport airport) {
		if (items.stream().filter(item -> item.equals(airport)).findAny().isEmpty()) {
			items.add(airport);
		}
		return airport;
	}

	public Optional<Airport> findById(String iata) {
		return items.stream().filter(item -> item.getIata().equalsIgnoreCase(iata)).findFirst();
	}
}
