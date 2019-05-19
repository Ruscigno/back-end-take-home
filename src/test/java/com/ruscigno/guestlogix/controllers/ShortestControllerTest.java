package com.ruscigno.guestlogix.controllers;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ruscigno.guestlogix.error.NotFoundException;
import com.ruscigno.guestlogix.services.DijkstraServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ShortestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private DijkstraServiceImpl djkService;

	private static final String URL = "/api/v1/routes/shortest";
	private static final String URL_PARAM = "?destination=%s&origin=%s";
	private static final String URL_BAD_REQUEST = "/api/v1/routes/shortest";
	private static final String ORIGIN_IATA = "ABJ";
	private static final String DESTINATION_IATA = "BJM";
	private static final String NOT_FOUND_IATA = "RUSCIGNO";
	private static final List<String> RESULT = new ArrayList<>(Arrays.asList("ABJ", "BRU", "BJM"));
	private static final String RESULT_RAW = "[\"ABJ\",\"BRU\",\"BJM\"]";

	@Test
	public void findShortestRouteValidTest() throws Exception {
		BDDMockito.given(this.djkService.findShortestRoute(Mockito.anyString(), Mockito.anyString()))
				.willReturn(Optional.of(RESULT));

		MvcResult result = mvc.perform(MockMvcRequestBuilders
				.get(String.format(URL + URL_PARAM, ORIGIN_IATA, DESTINATION_IATA)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().equalsIgnoreCase(RESULT_RAW));
	}

	@Test
	public void findShortesGeneralNotFoundTest() throws Exception {
		BDDMockito.given(this.djkService.findShortestRoute(Mockito.anyString(), Mockito.anyString()))
				.willReturn(Optional.empty());

		mvc.perform(MockMvcRequestBuilders.get(String.format(URL + URL_PARAM, ORIGIN_IATA, DESTINATION_IATA))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.status").value("NOT_FOUND"))
				.andExpect(jsonPath("$.message").value(DijkstraServiceImpl.NOT_FOUND_ANY_ROURE))
				.andExpect(jsonPath("$.errors").value(new ArrayList<>()));
	}

	@Test
	public void urlBadRequestTest() throws Exception {
		BDDMockito.given(this.djkService.findShortestRoute(Mockito.anyString(), Mockito.anyString()))
				.willReturn(Optional.of(RESULT));

		mvc.perform(MockMvcRequestBuilders.get(String.format(URL_BAD_REQUEST)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void invalidOriginIataCodeTest() throws Exception {
		invalidIataCode(ORIGIN_IATA, "origin");
	}

	@Test
	public void invalidDestinationIataCodeTest() throws Exception {
		invalidIataCode(DESTINATION_IATA, "destination");
	}

	private void invalidIataCode(String url, String key) throws Exception {
		String error = String.format(DijkstraServiceImpl.ERROR_MESSAGE, key, NOT_FOUND_IATA);
		List<String> errors = new ArrayList<>();
		errors.add(error);

		BDDMockito.given(this.djkService.findShortestRoute(Mockito.anyString(), Mockito.anyString()))
				.willThrow(new NotFoundException(DijkstraServiceImpl.NOT_FOUND_ANY_ROURE, errors));

		mvc.perform(MockMvcRequestBuilders.get(String.format(URL + URL_PARAM, url, NOT_FOUND_IATA))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.status").value("NOT_FOUND"))
				.andExpect(jsonPath("$.message").value(DijkstraServiceImpl.NOT_FOUND_ANY_ROURE))
				.andExpect(jsonPath("$.errors").value(errors.toString().replace("[", "").replace("]", "")));
	}

}