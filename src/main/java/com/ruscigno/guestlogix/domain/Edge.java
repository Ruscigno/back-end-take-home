package com.ruscigno.guestlogix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Edge  {
    private final String id;
    private final Vertex source;
    private final Vertex destination;
}