package com.ruscigno.guestlogix.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Airport {
	private String iata;
	private String name;
	private String city;
	private String country;
	private String latitute;
	private String longitude;
}
