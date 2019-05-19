package com.ruscigno.guestlogix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Airport;
import com.ruscigno.guestlogix.repositories.AirportRepositoryImpl;

@Service
public class AiportServiceImpl {
	@Autowired
	private AirportRepositoryImpl repository;

	public void insert(Airport aiport) {
		repository.save(aiport);
	}

	public List<Airport> findAll() {
		return repository.findAll();
	}

	public Optional<Airport> findById(String iata) {
		return repository.findById(iata);
	}
}
