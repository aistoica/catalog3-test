package com.endava.catalog.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties( ignoreUnknown = true )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Teacher {

	private String firstName;
	private String lastName;
	private String cnp;
	private Integer salary;
	@JsonFormat( pattern = "yyyy-MM-dd" )
	private LocalDate dateOfBirth;
}
