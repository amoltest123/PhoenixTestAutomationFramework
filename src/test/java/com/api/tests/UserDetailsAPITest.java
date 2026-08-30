package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class UserDetailsAPITest {
	
	@Test(description = "Verify if the UserDetails API response is shown correctly", groups = {"api", "regression", "smoke"})
	public void userDetailsAPITest() throws IOException {
		
		given()
			.spec(requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(responseSpec_OK())
			.and()
			.body(matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
	}
}
