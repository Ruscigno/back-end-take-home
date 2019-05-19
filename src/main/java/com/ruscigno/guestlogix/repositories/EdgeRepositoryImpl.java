package com.ruscigno.guestlogix.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ruscigno.guestlogix.domain.Edge;

@Component
public class EdgeRepositoryImpl{
	private List<Edge> items = new ArrayList<>();
	
	public List<Edge> findAll(){
		return items;
	}
	
	public Edge save(Edge edge) {
		if (items.stream().filter(item -> !item.equals(edge)).findAny().isPresent()) {
			items.add(edge);
		}
		return edge;
	}
}
