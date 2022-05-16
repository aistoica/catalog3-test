package com.endava.catalog;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.endava.catalog.clients.StudentClient;
import com.endava.catalog.clients.SubjectClient;
import com.endava.catalog.clients.TeacherClient;
import com.endava.catalog.data.DataGenerator;
import com.endava.catalog.models.Student;
import com.endava.catalog.models.Subject;
import com.endava.catalog.models.Teacher;

import io.restassured.response.Response;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FirstTest {

	private TeacherClient teacherClient = new TeacherClient();
	private StudentClient studentClient = new StudentClient();
	private SubjectClient subjectClient = new SubjectClient();
	private DataGenerator dataGenerator = new DataGenerator();

	@Test
	public void shouldGetTeacherList() {
		//GIVEN
		Teacher teacher = dataGenerator.generateTeacher();
		final Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );

		//WHEN
		final Response teacherListResponse = teacherClient.getTeacherList();

		//THEN
		teacherListResponse.then().statusCode( HttpStatus.SC_OK );
		final List<Teacher> teacherList = teacherListResponse.jsonPath().getList( "", Teacher.class );
		assertThat( teacherList, hasItem( teacher ) );
	}

	@Test
	public void shouldCreateTeacher() {
		//GIVEN
		Teacher teacher = dataGenerator.generateTeacher();

		//WHEN
		final Response response = teacherClient.createTeacher( teacher );

		//THEN
		response.then()
				.statusCode( HttpStatus.SC_OK )
				.body( "id", is( notNullValue() ) )
				.body( "firstName", is( teacher.getFirstName() ) )
				.body( "lastName", is( teacher.getLastName() ) )
				.body( "cnp", is( teacher.getCnp() ) )
				.body( "salary", is( teacher.getSalary() ) )
				.body( "dateOfBirth",
						is( teacher.getDateOfBirth().format( DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) ) ) )
				.body( "subjects", hasSize( 0 ) );

		final Teacher actualTeacher = response.as( Teacher.class );
		assertThat( actualTeacher, is( teacher ) );
	}

	@Test
	public void shouldGetTeacherById() {
		//GIVEN
		Teacher teacher = dataGenerator.generateTeacher();
		final Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		Integer teacherId = createTeacherResponse.jsonPath().getInt( "id" );

		//WHEN
		final Response response = teacherClient.getTeacherById( teacherId );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
		final Teacher actualTeacher = response.as( Teacher.class );
		assertThat( actualTeacher, is( teacher ) );
	}

	@Test
	public void shouldAssignSubjectToTeacher() {
		//WHEN
		final Response response = teacherClient.assignTeacherToSubject( 2, 1 );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
	}

	@Test
	public void shouldDeleteTeacher() {
		//GIVEN
		Teacher teacher = dataGenerator.generateTeacher();
		final Response createTeacherResponse = teacherClient.createTeacher( teacher );
		createTeacherResponse.then().statusCode( HttpStatus.SC_OK );
		Integer teacherId = createTeacherResponse.jsonPath().getInt( "id" );

		//WHEN
		final Response response = teacherClient.deleteTeacher( teacherId );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
	}

	@Test
	public void shouldGetStudentList() {
		//WHEN
		final Response response = studentClient.getStudentList( 0, 10 );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
	}

	@Test
	public void shouldEnrollStudentToSubject() {
		//GIVEN
		final Student student = dataGenerator.generateStudent();
		final Response createStudentResponse = studentClient.createStudent( student );
		createStudentResponse.then().statusCode( HttpStatus.SC_OK );
		Integer studentId = createStudentResponse.jsonPath().getInt( "id" );

		final Subject subject = dataGenerator.generateSubject();
		final Response createSubjectResponse = subjectClient.createSubject( subject );
		createSubjectResponse.then().statusCode( HttpStatus.SC_OK );
		Integer subjectId = createSubjectResponse.jsonPath().getInt( "id" );

		//WHEN
		final Response response = studentClient.enrollStudentToSubject( studentId, subjectId, true );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK )
				.body( "enrolledSubjects.id", hasItem( subjectId ) );
	}

	@Test
	public void shouldCreateStudent() {
		//GIVEN
		final Student student = dataGenerator.generateStudent();
		student.setAverageGrade( new BigDecimal( "9.5" ) );

		//WHEN
		final Response response = studentClient.createStudent( student );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
		final Student actualStudent = response.as( Student.class );
		assertThat( actualStudent, is( student ) );
	}

	@Test
	public void shouldCreateSubject() {
		//GIVEN
		final Subject subject = dataGenerator.generateSubject();

		//WHEN
		final Response response = subjectClient.createSubject( subject );

		//THEN
		response.then().statusCode( HttpStatus.SC_OK );
		final Subject actualSubject = response.as( Subject.class );
		assertThat( actualSubject, is( subject ) );
	}
}
