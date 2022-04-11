package com.meta.gaming;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*; 

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
	
	
	//@Test
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
	
	
	@Test
	public void AssertStudent()
	{
		System.out.println("===Hamcrest====");

		Response response = RestAssured.given().contentType("application/json").
								when().get("/student/list");
		
		
		
		//Hard Assert of Hamcrest
//		response.then() 
//					.body("firstName[1]", equalTo("Murphy"))//pass
//					.body("courses[1][1]", equalTo("StatisticsXYZ")) //fail
//					.body("programme[1]",equalTo("Financial Analysis"))//pass
//					.body("email[1]",equalTo("faucibus.orci.luctus@Duisac.netXYZ"));//fail
//		
		//Soft Assert of Hamcrest
//		 response.then() 
//		         .body("firstName[1]", equalTo("Murphy") ,//pass
//		        		 "courses[1][1]",equalTo("StatisticsXYZ"), //fail
//		        		 "programme[1]", equalTo("Financial Analysis"),//pass
//		        		 "email[1]",equalTo("faucibus.orci.luctus@Duisac.netXYZ")//fail
//		        		 );
//		 

		//use import static org.hamcrest.Matchers.*; 
		//http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
		response.then().body("courses[1]", hasItem( "Statistics"));
		response.then().body("id[1]", greaterThan(1));
		response.then().body("firstName[1]", equalToIgnoringCase( "murphY"));
		
		
	}
}
