package com.meta.gaming;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

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
	
	
	//@Test//----------Monday-----------
	public void HamcrestAssertStudent()
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


	//@Test--Thursday
	public void JSONSIMPLE() throws ParseException
	{
		Response response = RestAssured.
				given()
						.pathParam("myPath", "list").
						queryParam("programme", "Computer Science").queryParam("limit", "3")
						.contentType("application/json").
		     	when().get("/student/{myPath}");
		
		JSONParser parse= new JSONParser();
		
		JSONArray myJSON=(JSONArray) parse.parse(response.getBody().asString());
		
		for (int i = 0; i < myJSON.size(); i++) {
				
			JSONObject myObject = (JSONObject) myJSON.get(i);
			
			System.out.println( myObject.get("email"));
			
		}
		
	}
	
	//@Test//--Thursday
	public void JSONSIMPLEAnotherExample() throws ParseException
	{
		Response response = RestAssured.
				    given()
				       .baseUri("https://reqres.in").contentType("application/json").
			     	when().get("/api/users?page=2");
		
		System.out.println(response.getBody().asString());
		
		JSONParser parser= new JSONParser();
		
		JSONObject allData   =(JSONObject) parser.parse(response.getBody().asString());
		System.out.println("============");
		System.out.println(allData.get("total"));
		System.out.println(allData.get("page"));
		System.out.println(allData.get("data"));
		System.out.println(allData.get("support"));
		
		JSONArray  dataJson=(JSONArray) allData.get("data");
		JSONObject firstposition= (JSONObject) dataJson.get(1);
					System.out.println(firstposition.get("first_name"));
					System.out.println(firstposition.get("last_name"));
		
		JSONObject supportData=(JSONObject) allData.get("support");
			System.out.println(supportData.get("url"));
			System.out.println(supportData.get("text"));
		
	}
	
		
		@Test//----------Tuesday----------
		public void JSONAssertStudentbutUsingJSONSIMPLE() throws IOException, JSONException, ParseException
		{
					Response response = RestAssured.
									given()
										.pathParam("myPath", "list").
										queryParam("programme", "Computer Science").queryParam("limit", "3")
										.contentType("application/json").
							     	when().get("/student/{myPath}");
					
					String actual= response.getBody().asString();
					System.out.println(actual);
					
					Reader read= new FileReader("Resource\\myJsonFile.json");
					JSONParser parse= new JSONParser();
					String expected=parse.parse(read).toString();
					System.out.println(expected);
					
					JSONAssert.assertEquals(expected, actual, JSONCompareMode.LENIENT);
		}
}
