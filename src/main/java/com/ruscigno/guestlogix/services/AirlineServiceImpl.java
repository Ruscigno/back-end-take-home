package com.ruscigno.guestlogix.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Airline;
import com.ruscigno.guestlogix.repositories.AirlineRepositoryImpl;

@Service
public class AirlineServiceImpl {
	@Autowired
	private AirlineRepositoryImpl repository;

	public void insert(Airline airline) {
		repository.save(airline);
	}

	public List<Airline> findAll() {
		return repository.findAll();
	}

	public Optional<Airline> findById(String name) {
		return repository.findById(name);
	}
}
