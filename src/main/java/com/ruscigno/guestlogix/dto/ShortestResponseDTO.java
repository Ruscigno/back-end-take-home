package com.ruscigno.guestlogix.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruscigno.guestlogix.domain.Airline;
import com.ruscigno.guestlogix.domain.Airport;

import lombok.Data;

@Data
public class ShortestResponseDTO {
	private List<String> shortesRoute = new ArrayList<>();
	private Map<String, Airline> airlines = new HashMap<>();
	private Map<String, Airport> airpors = new HashMap<>();
}
