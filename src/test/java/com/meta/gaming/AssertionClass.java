package com.meta.gaming;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AssertionClass {
	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
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


	//@Test//----------Monday-----------
	public void HamcrestAssertStudent()
	{
		System.out.println("===Hamcrest====");

		Response response = RestAssured.given().contentType("application/json").
				when().get("/student/list");



		//Hard Assert of Hamcrest
		//			response.then() 
		//						.body("firstName[1]", equalTo("Murphy"))//pass
		//						.body("courses[1][1]", equalTo("StatisticsXYZ")) //fail
		//						.body("programme[1]",equalTo("Financial Analysis"))//pass
		//						.body("email[1]",equalTo("faucibus.orci.luctus@Duisac.netXYZ"));//fail
		//			
		//Soft Assert of Hamcrest
		//			 response.then() 
		//			         .body("firstName[1]", equalTo("Murphy") ,//pass
		//			        		 "courses[1][1]",equalTo("StatisticsXYZ"), //fail
		//			        		 "programme[1]", equalTo("Financial Analysis"),//pass
		//			        		 "email[1]",equalTo("faucibus.orci.luctus@Duisac.netXYZ")//fail
		//			        		 );
		//			 

		//use import static org.hamcrest.Matchers.*; 
		//http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html
		response.then().body("courses[1]", hasItem( "Statistics"));
		response.then().body("id[1]", greaterThan(1));
		response.then().body("firstName[1]", equalToIgnoringCase( "murphY"));
	}


	//@Test//----------Tuesday----------
	public void JSONAssertStudent() throws IOException, JSONException
	{
		Response response = RestAssured.
				given()
				.pathParam("myPath", "list").
				queryParam("programme", "Computer Science").queryParam("limit", "3")
				.contentType("application/json").
				when().get("/student/{myPath}");

		String actual= response.getBody().asString();
		System.out.println(actual);

		String expected = new String(Files.readAllBytes(Paths.get("Resource\\data.txt")));
		System.out.println(expected);

		JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);

		//Assert.assertEquals(expected, actual);
	}
}
