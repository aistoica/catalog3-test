package com.endava.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties( ignoreUnknown = true )
public class Subject {

	private String name;
	private Boolean optional;
	private Integer creditPoints;
}
