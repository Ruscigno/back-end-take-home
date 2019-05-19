package com.ruscigno.guestlogix.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Route{
	private String airlineId;
	private String origin;
	private String destination;
}
