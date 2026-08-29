package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.pojos.UserCredentials;
import com.api.utils.SpecUtils;

public class LoginAPITest {

	@Test
	public void loginAPITest(){
		
		UserCredentials userCredentials = new UserCredentials("iamfd", "password");
		given()
			.spec(SpecUtils.requestSpec(userCredentials))
		.when()
			.post("login")
		.then()
			.spec(SpecUtils.responseSpec_OK())
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response_schema/LoginResponseScema.json"));
	}

}
