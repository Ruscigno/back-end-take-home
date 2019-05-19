package com.ruscigno.guestlogix.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ruscigno.guestlogix.domain.Edge;
import com.ruscigno.guestlogix.domain.Route;
import com.ruscigno.guestlogix.domain.Vertex;
import com.ruscigno.guestlogix.services.EdgeServiceImpl;
import com.ruscigno.guestlogix.services.RouteServiceImpl;
import com.ruscigno.guestlogix.services.VertexServiceImpl;
import com.ruscigno.guestlogix.utils.CsvLoader;

@Configuration
public class GuestlogixConfig {

	private static final Logger log = LoggerFactory.getLogger(GuestlogixConfig.class);

	@Autowired
	private RouteServiceImpl routeService;
	
	@Autowired
	private VertexServiceImpl nodeService;
	
	@Autowired
	private EdgeServiceImpl edgeService;

	@Bean
	public void loadDate() {
		log.info("Loading data... please wait");
		CsvLoader.loadObjectList(Route.class, "data/routes.csv").forEach(item -> loadRoutes(item));
	}

	private void loadRoutes(Route route) {
		routeService.insert(route);
		createNodes(route);
	}

	private void createNodes(Route route) {
		Vertex origin = new Vertex(route.getOrigin());
		Vertex destination = new Vertex(route.getDestination());
		nodeService.insert(origin);
		nodeService.insert(destination);
		edgeService.insert(new Edge(route.toString(), origin, destination));
	}
}