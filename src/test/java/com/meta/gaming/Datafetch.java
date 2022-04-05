package com.meta.gaming;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class Datafetch {

	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
	}

	//@Test
	public void getAllStudent()
	{
		System.out.println("===getAllStudent====");

		List<String> allvalue =	RestAssured.
							given().contentType("application/json").
								when().get("/student/list").
									then().extract().path("courses[2]");
		
		System.out.println("===="+allvalue);//====[Calculus, Algorithms, Software Development, Ethics]
		
		
		String singlevalue =	RestAssured.
				given().contentType("application/json").
					when().get("/student/list").
						then().extract().path("courses[2][1]");
		
		System.out.println("===="+singlevalue);//====Algorithms
		
	}	
	
	
	@Test
	public void getindiStudent()
	{
		System.out.println("===getindividualStudent====");

		List<String> value =	RestAssured.
							given().contentType("application/json").
								when().get("/student/71").
									then().extract().path("courses");
		
		System.out.println("===="+value);
	}	
}
