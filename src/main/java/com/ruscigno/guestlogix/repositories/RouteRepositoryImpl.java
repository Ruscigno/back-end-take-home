package com.ruscigno.guestlogix.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ruscigno.guestlogix.domain.Route;

@Component
public class RouteRepositoryImpl{
	private List<Route> items = new ArrayList<>();
	
	public List<Route> findAll(){
		return items;
	}
	
	public Route save(Route route) {
		if (!items.stream().filter(item -> item.equals(route)).findAny().isPresent()) {
			items.add(route);
		}
		return route;
	}
}
