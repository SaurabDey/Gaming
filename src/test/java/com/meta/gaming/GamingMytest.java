package com.meta.gaming;




import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;

public class GamingMytest {

	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
	}

	//@Test
	public void getAllStudent()
	{
		System.out.println("===getAllStudent====");

		RestAssured.
		given().contentType("application/json").
		when().get("/student/list").
		prettyPrint();
	}	

	//@Test
	public void getIndividualStudent()
	{
		System.out.println("===getIndividualStudent====");

		RestAssured.
		given().contentType("application/json").
		when().get("/student/10").
		prettyPrint();
	}
	
	@Test
	public void getAllStudentQueryParam()
	{
		System.out.println("===getAllStudentpathQueryParam====");

		RestAssured.
			given()
			   .pathParam("myPath", "list").
			     queryParam("programme", "Computer Science").queryParam("limit", "3")
			     .contentType("application/json").
			when().get("/student/{myPath}").
			prettyPrint();
	}	
	//@Test
	public void createStudent()
	{
		System.out.println("===createStudent====");
		Faker faker = new Faker();
		
		List<String> cour= new ArrayList<>();
		cour.add("Math1");
		cour.add("Math2");
		cour.add("Math3");

		Student student= new Student();
		student.setFirstName(faker.name().firstName());
		student.setLastName(faker.name().lastName());
		student.setEmail(faker.internet().emailAddress());
		student.setProgramme(faker.book().publisher());
		student.setCourses(cour);
		
		RestAssured.
		given().contentType("application/json").body(student).
		when().post("/student").
		prettyPrint();
	}

	//@Test
	public void updateStudent()
	{
		System.out.println("===updateStudent====");
		Faker faker = new Faker();
		
		List<String> cour= new ArrayList<>();
		cour.add("Machine Learning");
		cour.add("Automation");
		cour.add("manual");

		Student student= new Student();
		student.setFirstName(faker.name().firstName());
		student.setLastName(faker.name().firstName());
		student.setEmail(faker.internet().emailAddress());
		student.setProgramme(faker.book().publisher());
		student.setCourses(cour);
		
		RestAssured.
		given().contentType("application/json").body(student).
		when().put("/student/101").
		prettyPrint();
	}

	//@Test
	public void patchStudent()
	{
		System.out.println("===patchStudent====");
		
		
		Student student= new Student();
		student.setEmail("saurab.dey@gmail.com");
		
		RestAssured.
		given().contentType("application/json").body(student).
		when().patch("/student/101").
		prettyPrint();
	}

	//@Test
	public void deleteStudent()
	{
		System.out.println("===deleteStudent====");

		RestAssured.
		given().contentType("application/json").
		when().delete("/student/101").
		prettyPrint();
	}

}
