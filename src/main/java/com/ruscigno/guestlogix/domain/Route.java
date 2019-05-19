package com.ruscigno.guestlogix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route{
//	private String id;
	private String airlineId;
	private String origin;
	private String destination;
}
