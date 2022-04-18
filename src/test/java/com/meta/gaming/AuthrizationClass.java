package com.meta.gaming;

import java.io.File;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthrizationClass {
	
	//@Test
	public void noAuth()
	{
//		RestAssured.baseURI="http://localhost:9090";
//
//		Response response = RestAssured.
//				given()
//				.pathParam("myPath", "list").
//				queryParam("programme", "Computer Science").queryParam("limit", "3")
//				.contentType("application/json").
//				when().get("/student/{myPath}");
//		
//		response.then().log().all();
		
		RestAssured.baseURI="https://reqres.in/api";

		Response response = RestAssured.
				given()
				.contentType("application/json").
				when().get("/users?page=2");
		
		response.then().log().all();
		
	}
	//@Test
	public void basicAuth()
	{
		
		RestAssured.baseURI="https://the-internet.herokuapp.com/basic_auth";

		/*
		 * Response response = RestAssured. given() .auth().basic("admin", "admin")
		 * .contentType("application/json"). when().get();
		 * 
		 * response.then().log().all();
		 */
		
		Response response = 
				RestAssured. 
				 given() 
				 .auth().preemptive().basic("admin", "admin")  //Returns the preemptive authentication view. This means that the authentication details are sent in the requestheader regardless if the server has challenged for authentication or not.
				 .contentType("application/json"). 
				 when().get();
		 
		 response.then().log().all();
		 
		
		/*
		 * Response response = 
		 * RestAssured. given() 
		 * 			.header("Authorization","Basic YWRtaW46YWRtaW4=") 
		 * 			.contentType("application/json"). 
		 *           when().get();
		 * 
		 * response.then().log().all();
		 */
		
	}
	
	@Test //https://developers.zamzar.com/user
	public void apiAuth()
	{
		RestAssured.baseURI="https://sandbox.zamzar.com/v1/jobs";
		
		 Response response = RestAssured. 
				 given() 
				 .multiPart("source_file", new File("Resource/API Testing Promotion.JPG")) 
				 .multiPart("target_format","png") 
				 .auth().basic("9fcc941f48252647b588693c102a5d47af0c3a6a", "").
		 when().post();
		 
		
		/*----This didnot work----
		 * Response response = RestAssured. given() .header(
		 * "Authorization","Basic OWZjYzk0MWY0ODI1MjY0N2I1ODg2OTNjMTAyYTVkNDdhZjBjM2E2YQ=="
		 * ) .multiPart("source_file", new File("Resource/API Testing Promotion.JPG"))
		 * .multiPart("target_format", "png") .when().post();
		 * 
		 */
		 response.then().log().all();
		
	}
	//@Test
	public void oath01Auth()
	{
		//TBD
	}

}
