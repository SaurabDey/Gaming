package com.meta.gaming;




import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class GamingMytest {

	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:6060";
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
	public void createStudent()
	{
		System.out.println("===createStudent====");
		
		List<String> cour= new ArrayList<>();
		cour.add("Phys");
		cour.add("Chem");
		cour.add("Math");

		Student student= new Student();
		student.setFirstName("Dev");
		student.setLastName("Test");
		student.setEmail("Dev.testShekdar@gmail.com");
		student.setProgramme("DataScience");
		student.setCourses(cour);
		
		RestAssured.
		given().contentType("application/json").body(student).
		when().post("/student").
		prettyPrint();
	}

	@Test
	public void updateStudent()
	{
		System.out.println("===updateStudent====");

		List<String> cour= new ArrayList<>();
		cour.add("Machine Learning");
		cour.add("Automation");
		cour.add("manual");

		Student student= new Student();
		student.setFirstName("Update");
		student.setLastName("Student");
		student.setEmail("Update.Student@gmail.com");
		student.setProgramme("DataScience");
		student.setCourses(cour);
		
		RestAssured.
		given().contentType("application/json").body(student).
		when().put("/student/101").
		prettyPrint();
	}

	@Test
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

	@Test
	public void deleteStudent()
	{
		System.out.println("===deleteStudent====");

		RestAssured.
		given().contentType("application/json").
		when().delete("/student/101").
		prettyPrint();
	}

}
