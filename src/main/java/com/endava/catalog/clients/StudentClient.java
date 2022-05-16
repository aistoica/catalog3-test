package com.endava.catalog.clients;

import com.endava.catalog.filters.LogFilter;
import com.endava.catalog.models.Student;
import com.endava.catalog.util.EnvReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentClient {

	private static final String CLIENT_PATH = "/student";

	public Response getStudentList(Integer page, Integer size) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.queryParams( "page", page, "size", size )
				.get();
	}

	public Response enrollStudentToSubject(Integer studentId, Integer subjectId, Boolean status) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.pathParam( "studentId", studentId )
				.pathParam( "subjectId", subjectId )
				.pathParam( "status", status )
				.post( "{studentId}/enrollment/{status}/{subjectId}" );
	}

	public Response createStudent( Student student ) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.body( student )
				.contentType( ContentType.JSON )
				.post();
	}
}
