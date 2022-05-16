package com.endava.catalog.data;

import com.endava.catalog.models.Student;
import com.endava.catalog.models.Subject;
import com.endava.catalog.models.Teacher;
import com.github.javafaker.Faker;

import java.time.ZoneId;

public class DataGenerator {

	private Faker faker = new Faker();

	public Teacher generateTeacher() {
		Teacher teacher = new Teacher();
		teacher.setFirstName( faker.name().firstName() );
		teacher.setLastName( faker.name().lastName() );
		teacher.setCnp( faker.number().digits( 13 ) );
		teacher.setSalary( faker.number().numberBetween( 1, 10000 ) );
		teacher.setDateOfBirth( faker.date().birthday( 30, 60 ).toInstant().atZone( ZoneId.systemDefault() ).toLocalDate() );
		return teacher;
	}

	public Subject generateSubject() {
		Subject subject = new Subject();
		subject.setName( faker.educator().course() );
		subject.setOptional( faker.random().nextBoolean() );
		subject.setCreditPoints( faker.number().numberBetween( 1, 20 ) );
		return subject;
	}

	public Student generateStudent() {
		Student student = new Student();
		student.setFirstName( faker.name().firstName() );
		student.setLastName( faker.name().lastName() );
		return student;
	}
}
