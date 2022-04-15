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

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*; 

public class DataFetchUsingJSONPathClass {

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
	public void getAllStudentVariousClassesInvolved()
	{
		System.out.println("===getAllStudent====");

		RequestSpecification reqSep = RestAssured.given().contentType("application/json");

		Response resp  = reqSep.when().get("/student/list");

		ValidatableResponse valid=resp.then();

		valid.statusCode(200);

		List<String> allvalue= valid.extract().path("courses[2]");

		String fN= valid.extract().path("firstName[2]");
	}

}
