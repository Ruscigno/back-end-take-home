package com.ruscigno.guestlogix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airline {
	private String threeDigitCode;
	private String name;
	private String twoDigitCode;
	private String country;
}
