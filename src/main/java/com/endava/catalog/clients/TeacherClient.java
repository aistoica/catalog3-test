package com.endava.catalog.clients;

import com.endava.catalog.filters.LogFilter;
import com.endava.catalog.models.Teacher;
import com.endava.catalog.util.EnvReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TeacherClient {

	private static final String CLIENT_PATH = "/teacher";

	public Response getTeacherList() {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.get( "" );
	}

	public Response createTeacher( Teacher teacher) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.body( teacher )
				.contentType( ContentType.JSON )
				.post();
	}

	public Response getTeacherById(Integer id) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.pathParam( "id", id )
				.get( "{id}" );
	}

	public Response assignTeacherToSubject(Integer teacherId, Integer subjectId) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.pathParam( "teacherId", teacherId )
				.pathParam( "subjectId", subjectId )
				.put( "{teacherId}/subject/{subjectId}" );
	}

	public Response deleteTeacher(Integer id) {
		return RestAssured.given().filters( new LogFilter() )
				.baseUri( EnvReader.getBaseUri() )
				.port( EnvReader.getPort() )
				.basePath( EnvReader.getBasePath() + CLIENT_PATH )
				.queryParams( "id", id )
				.delete();
	}
}
