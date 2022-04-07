package com.meta.gaming;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class MyClass {

	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
	}

	@Test
	public void getAllStudent()
	{
		System.out.println("===getAllStudent====");

		RequestSpecification reqSep = RestAssured.given().contentType("application/json");

		Response resp  = reqSep.when().get("/student/list");

		ValidatableResponse valid=resp.then();

		valid.statusCode(200);

		List<String> allvalue= valid.extract().path("courses[2]");

		String fN= valid.extract().path("firstName");

		/*
		 * List<String> allvalue = 
		 * RestAssured. given().contentType("application/json").when().get("/student/list").then().extract().path("courses[2]");
		 * 
		 * String fn = 
		 * RestAssured. given().contentType("application/json").when().get("/student/list").then().extract().path("firstname");
		 */
	}	

}
