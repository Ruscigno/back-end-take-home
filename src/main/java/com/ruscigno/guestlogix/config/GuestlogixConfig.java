package com.ruscigno.guestlogix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruscigno.guestlogix.domain.Airline;
import com.ruscigno.guestlogix.domain.Airport;
import com.ruscigno.guestlogix.domain.Edge;
import com.ruscigno.guestlogix.domain.Route;
import com.ruscigno.guestlogix.domain.Vertex;
import com.ruscigno.guestlogix.services.AiportServiceImpl;
import com.ruscigno.guestlogix.services.AirlineServiceImpl;
import com.ruscigno.guestlogix.services.EdgeServiceImpl;
import com.ruscigno.guestlogix.services.RouteServiceImpl;
import com.ruscigno.guestlogix.services.VertexServiceImpl;
import com.ruscigno.guestlogix.utils.CsvLoader;

@Configuration
public class GuestlogixConfig {

	private static final Logger log = LoggerFactory.getLogger(GuestlogixConfig.class);
	private static final String ASK_ADMIN = "Aks system administrator do insert this record in our database";

	@Autowired
	private AiportServiceImpl aiportService;

	@Autowired
	private AirlineServiceImpl airlineService;

	@Autowired
	private RouteServiceImpl routeService;
	
	@Autowired
	private VertexServiceImpl nodeService;
	
	@Autowired
	private EdgeServiceImpl edgeService;

	@Bean
	public void loadDate() {
		log.info("Loading data... please wait");
		CsvLoader.loadObjectList(Airport.class, "data/airports.csv").forEach(item -> aiportService.insert(item));
		CsvLoader.loadObjectList(Airline.class, "data/airlines.csv").forEach(item -> airlineService.insert(item));
		CsvLoader.loadObjectList(Route.class, "data/routes.csv").forEach(item -> loadRoutes(item));
	}

	private void loadRoutes(Route route) {
		routeService.insert(route);
		checkAirlieAndAirports(route);
		createNodes(route);
	}

	private void createNodes(Route route) {
		Vertex origin = new Vertex(route.getOrigin());
		Vertex destination = new Vertex(route.getDestination());
		nodeService.insert(origin);
		nodeService.insert(destination);
		edgeService.insert(new Edge(route.toString(), origin, destination));
	}

	private void checkAirlieAndAirports(Route item) {
		if (aiportService.findById(item.getOrigin()).isEmpty())
			insertAirport(item.getOrigin());

		if (aiportService.findById(item.getDestination()).isEmpty())
			insertAirport(item.getDestination());

		if (airlineService.findById(item.getAirlineId()).isEmpty())
			insertAirline(item);
	}

	private void insertAirline(Route route) {
		Airline airline = new Airline();
		airline.setTwoDigitCode(route.getAirlineId());
		airline.setName(ASK_ADMIN);
		airlineService.insert(airline);
	}

	private void insertAirport(String iataCode) {
		Airport aiport = new Airport();
		aiport.setIata(iataCode);
		aiport.setName(ASK_ADMIN);
		aiportService.insert(aiport);
	}
}