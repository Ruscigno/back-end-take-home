package com.ruscigno.guestlogix.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ruscigno.guestlogix.domain.Edge;
import com.ruscigno.guestlogix.domain.Route;
import com.ruscigno.guestlogix.domain.Vertex;
import com.ruscigno.guestlogix.error.NotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DijkstraServiceImplTest {

	@MockBean
	private VertexServiceImpl nodeService;

	@MockBean
	private EdgeServiceImpl edgeService;

	@Autowired
	private DijkstraServiceImpl dijkstraService;

	private static final String INVALID = "OUA";
	private static final String ORIGEM = "ABJ";
	private static final String STOP_01 = "BRU";
	private static final String DESTINATION = "BJM";
	private static final List<String> RESPONSE = new ArrayList<>(Arrays.asList(ORIGEM, STOP_01, DESTINATION));

	private List<Vertex> nodes = new ArrayList<>();
	private List<Edge> edges = new ArrayList<>();
	private Vertex vert01 = new Vertex(ORIGEM);
	private Vertex vert02 = new Vertex(STOP_01);
	private Vertex vert03 = new Vertex(DESTINATION);

	private Route route01 = new Route("AC", vert01.getName(), vert02.getName());
	private Route route02 = new Route("AC", vert02.getName(), vert03.getName());
	private Edge edge01 = new Edge(route01.toString(), vert01, vert02);
	private Edge edge02 = new Edge(route02.toString(), vert02, vert03);

	@Before
	public void setUp() throws Exception {
		nodes.add(vert01);
		nodes.add(vert02);
		nodes.add(vert03);
		edges.add(edge01);
		edges.add(edge02);
		BDDMockito.given(this.nodeService.findAll()).willReturn(nodes);
		BDDMockito.given(this.edgeService.findAll()).willReturn(edges);
	}

	@Test
	public void validAirportsTest() throws Exception {
		Optional<List<String>> result = dijkstraService.findShortestRoute(ORIGEM, DESTINATION);
		assertEquals(RESPONSE, result.get());
	}

	@Test(expected = NotFoundException.class)
	public void invalidOriginTest() {
		dijkstraService.findShortestRoute(INVALID, DESTINATION);
	}

	@Test(expected = NotFoundException.class)
	public void invalidDestinationTest() {
		dijkstraService.findShortestRoute(ORIGEM, INVALID);
	}
}
