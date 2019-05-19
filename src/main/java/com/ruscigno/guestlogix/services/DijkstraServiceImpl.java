package com.ruscigno.guestlogix.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruscigno.guestlogix.domain.Graph;
import com.ruscigno.guestlogix.domain.Vertex;
import com.ruscigno.guestlogix.utils.DijkstraAlgorithm;

@Service
public class DijkstraServiceImpl {
	@Autowired
	private VertexServiceImpl nodeService;

	@Autowired
	private EdgeServiceImpl edgeService;

	Graph graph;
	DijkstraAlgorithm dijkstra;
	Optional<Vertex> originNode;
	Optional<Vertex> destinationNode;

	public Optional<List<String>> findShortestRoute(String origin, String destination) {
		dijkstraInit();

		if (checkAirports(origin, destination))
			return Optional.empty();

		LinkedList<Vertex> path = getPath();
		if (path.isEmpty())
			return Optional.empty();

		return createMeaningfulFeedback(path);
	}

	private Optional<List<String>> createMeaningfulFeedback(List<Vertex> path) {
		List<String> response = new ArrayList<>();
		for (Vertex item : path) {
			response.add(String.format("Airport %s: %s", response.size() + 1, item.getName()));
		}
		return Optional.of(response);
	}

	private LinkedList<Vertex> getPath() {
		dijkstra.execute(originNode.get());
		return dijkstra.getPath(destinationNode.get());
	}

	private boolean checkAirports(String origin, String destination) {
		originNode = nodeService.findAll().stream().filter(item -> item.getName().equalsIgnoreCase(origin)).findFirst();
		if (originNode.isEmpty())
			return true;

		destinationNode = nodeService.findAll().stream().filter(item -> item.getName().equalsIgnoreCase(destination))
				.findFirst();
		if (destinationNode.isEmpty())
			return true;

		return false;
	}

	private void dijkstraInit() {
		graph = new Graph(nodeService.findAll(), edgeService.findAll());
		dijkstra = new DijkstraAlgorithm(graph);
	}
}
