package com.endava.catalog.clients;

import com.endava.catalog.filters.LogFilter;
import com.endava.catalog.models.Subject;
import com.endava.catalog.util.EnvReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SubjectClient {

	private static final String CLIENT_PATH = "/subject";

	public Response createSubject( Subject subject ) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.body( subject )
				.contentType( ContentType.JSON )
				.post();
	}
}
