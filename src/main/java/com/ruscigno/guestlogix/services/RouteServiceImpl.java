package com.ruscigno.guestlogix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Route;
import com.ruscigno.guestlogix.repositories.RouteRepositoryImpl;

@Service
public class RouteServiceImpl {
	@Autowired
	private RouteRepositoryImpl repository;

	public void insert(Route route) {
		repository.save(route);
	}

	public List<Route> findAll() {
		return repository.findAll();
	}
}