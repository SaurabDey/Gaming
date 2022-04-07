package com.meta.gaming;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
		
		List<String> expectedList=new ArrayList<>();
		expectedList.add("Anatomy");
		expectedList.add("Biochemistry");
		expectedList.add("Genetics");
		expectedList.add("uman Behavior");
		
		//Junit
		Assert.assertEquals(expectedList, value);
		
		//TestNG
		org.testng.Assert.assertEquals(value, expectedList);
		
		//Hamcrest
		
	}	
}
