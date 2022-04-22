package com.meta.gaming;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Epic("Epic: Allure examples")
@Feature("Feature: TestNg support")
public class AllureReport {

	@Story("Story: Base support for bdd annotations")
	@Issue("https://www.seleniumeasy.com/")
	@Link("https://docs.qameta.io/allure/#_testng")
	@Test
	public void positive()
	{
		RestAssured.baseURI="https://reqres.in/api";

		Response response = RestAssured.
				given()
				.contentType("application/json").
				when().get("/users?page=2");

		response.then().log().all();
	}

	@Description("Description 1 : Testing Allure Report")
	@Step("Step 1 : Testing Allure Report")
	@TmsLink("https://hangouts.google.com")
	@Test
	public void negative()
	{
		Assert.assertEquals("Test", "test");
	}
}
