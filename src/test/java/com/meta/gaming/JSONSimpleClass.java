package com.meta.gaming;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JSONSimpleClass {
	@Before
	public void start()
	{
		RestAssured.baseURI="http://localhost:9090";
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


	//@Test//----------thursday----------
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

	@Test//--- FRIDAY
	public void createStudent()
	{
		System.out.println("===createStudent====");
		Faker faker = new Faker();

		List<String> cour= new ArrayList<>();
		cour.add("Math1");
		cour.add("Math2");
		cour.add("Math3");

		/*
		 * Student student= new Student();
		 * student.setFirstName(faker.name().firstName());
		 * student.setLastName(faker.name().lastName());
		 * student.setEmail(faker.internet().emailAddress());
		 * student.setProgramme(faker.book().publisher()); student.setCourses(cour);
		 */

		JSONObject sut= new JSONObject();
		sut.put("firstName", faker.name().firstName());
		sut.put("lastName", faker.name().lastName());
		sut.put("email", faker.internet().emailAddress());
		sut.put("programme", faker.book().publisher());
		sut.put("courses", cour);

		RestAssured.
		given().contentType("application/json").body(sut.toJSONString()).
		when().post("/student").
		prettyPrint();
	}
}
