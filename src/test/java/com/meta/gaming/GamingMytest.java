package com.meta.gaming;




import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class GamingMytest {
	
	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
	}
	
	@Test
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
	
	//@Test
	public void createStudent()
	{
		System.out.println("===createStudent====");
		
		String stud="{\r\n"
				+ "        \"firstName\": \"Saurab\",\r\n"
				+ "        \"lastName\": \"Changeit\",\r\n"
				+ "        \"email\": \"saurab@gmail.com\",\r\n"
				+ "        \"programme\": \"Maths\",\r\n"
				+ "        \"courses\": [\r\n"
				+ "            \"Maths1\",\r\n"
				+ "            \"Maths2\",\r\n"
				+ "            \"Maths2\"\r\n"
				+ "        ]\r\n"
				+ "    }";
		
		RestAssured.
	    given().contentType("application/json").body(stud).
	    when().post("/student").
	    prettyPrint();
	}
	
	//@Test
	public void updateStudent()
	{
		System.out.println("===updateStudent====");
		
		String stud="{\r\n"
				+ "        \"firstName\": \"Rajesh\",\r\n"
				+ "        \"lastName\": \"Changeit\",\r\n"
				+ "        \"email\": \"Rajesh@gmail.com\",\r\n"
				+ "        \"programme\": \"Science\",\r\n"
				+ "        \"courses\": [\r\n"
				+ "            \"Science1\",\r\n"
				+ "            \"Science2\",\r\n"
				+ "            \"Science2\"\r\n"
				+ "        ]\r\n"
				+ "    }";
		
		RestAssured.
	    given().contentType("application/json").body(stud).
	    when().put("/student/101").
	    prettyPrint();
	}
	
	//@Test
	public void patchStudent()
	{
		System.out.println("===patchStudent====");
		
		String stud="{\r\n"
				+ "	\"email\": \"DevyaniChanged@gmail.com\"\r\n"
				+ "}";
		
		RestAssured.
	    given().contentType("application/json").body(stud).
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
