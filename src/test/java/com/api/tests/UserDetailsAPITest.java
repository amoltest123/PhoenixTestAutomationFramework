package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

public class UserDetailsAPITest {
	
	@Test
	public void userDetailsAPITest() throws IOException {
		
		given()
			.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
		.then()
			.spec(SpecUtils.responseSpec_OK())
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response_schema/UserDetailsResponseSchema.json"));
	}
}
