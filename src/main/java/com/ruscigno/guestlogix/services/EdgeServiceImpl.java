package com.ruscigno.guestlogix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Edge;
import com.ruscigno.guestlogix.repositories.EdgeRepositoryImpl;

@Service
public class EdgeServiceImpl {
	@Autowired
	private EdgeRepositoryImpl repository;

	public void insert(Edge edge) {
		repository.save(edge);
	}

	public List<Edge> findAll() {
		return repository.findAll();
	}
}