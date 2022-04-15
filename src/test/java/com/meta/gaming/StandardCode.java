package com.meta.gaming;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StandardCode {
	RequestSpecification resSpec;
	ResponseSpecification responseSpec;
	@Before
	public void start()
	{
		
		RequestSpecBuilder rSB= new RequestSpecBuilder();
		rSB.setContentType("application/json");
		rSB.setBaseUri("http://localhost:9090");
	
		resSpec=rSB.build();
		
		
		ResponseSpecBuilder resposeSB= new ResponseSpecBuilder();
		resposeSB.expectContentType("application/json");
		resposeSB.expectStatusCode(200);
		resposeSB.expectResponseTime(lessThan(1000l),TimeUnit.MILLISECONDS);
		
		responseSpec=resposeSB.build();
	}

	@Test
	public void getAllStudent()
	{
		System.out.println("===getAllStudent====");

		Response res= RestAssured.
					given().spec(resSpec).
						when().get("/student/list");
		
		res.then().spec(responseSpec);
		
	}	

	@Test
	public void getIndividualStudent()
	{
		System.out.println("===getIndividualStudent====");

		Response res= RestAssured.
					given().spec(resSpec).
						when().get("/student/10");
		
		res.then().spec(responseSpec);
	}

	@Test
	public void patchStudent()
	{
		System.out.println("===patchStudent====");
		Faker faker = new Faker();

		Student student= new Student();
		student.setEmail(faker.internet().emailAddress());

		Response res= RestAssured.
					given().spec(resSpec).body(student).
						when().patch("/student/101");
		
		res.then().spec(responseSpec);
	}

}
