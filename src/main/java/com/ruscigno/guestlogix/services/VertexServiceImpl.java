package com.ruscigno.guestlogix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Vertex;
import com.ruscigno.guestlogix.repositories.VertexRepositoryImpl;

@Service
public class VertexServiceImpl {
	@Autowired
	private VertexRepositoryImpl repository;

	public void insert(Vertex vertex) {
		repository.save(vertex);
	}

	public List<Vertex> findAll() {
		return repository.findAll();
	}
}