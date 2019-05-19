package com.ruscigno.guestlogix.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruscigno.guestlogix.services.DijkstraServiceImpl;

@RestController
@RequestMapping("/api/v1/routes")
@CrossOrigin(origins = "*")
public class ShortestController {

	private static final Logger log = LoggerFactory.getLogger(ShortestController.class);
	private static final String ANY_ROURE = "Could not find any route to specified airports";

	@Autowired
	private DijkstraServiceImpl service;

	/**
	 * Retorna um employee dado um CPF.
	 * 
	 * @param cnpj
	 * @return ResponseEntity<Response<EmployeeDto>>
	 */
	@GetMapping(value = "shortest")
	public ResponseEntity<List<String>> getShortestRoute(
			@RequestParam(value = "origin", required = true) String origin,
			@RequestParam(value = "destination", required = true) String destination) {
		
		Optional<List<String>> shortesRoute = service.findShortestRoute(origin, destination);

		if (shortesRoute.isEmpty()) {
			log.info(ANY_ROURE);
			return ResponseEntity.badRequest().body(Arrays.asList(ANY_ROURE));
		}
		return ResponseEntity.ok(shortesRoute.get());
	}
}