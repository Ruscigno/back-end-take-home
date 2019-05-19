package com.ruscigno.guestlogix.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ruscigno.guestlogix.domain.Vertex;

@Component
public class VertexRepositoryImpl {
	private List<Vertex> items = new ArrayList<>();
	
	public List<Vertex> findAll(){
		return items;
	}
	
	public Vertex save(Vertex vertex) {
		if (items.stream().filter(item -> item.equals(vertex)).findAny().isEmpty()) {
			items.add(vertex);
		}
		return vertex;
	}
}
