package com.api.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.models.UserCredentials;
import static com.api.utils.SpecUtils.*;

public class LoginAPITest {
	
	private UserCredentials userCredentials;
	
	@BeforeMethod(description = "Create the Payload for the login api")
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
	}

	@Test(description = "Verifying if login api is working for user iamfd", groups = {"api", "regression", "smoke"})
	public void loginAPITest(){
		
		given()
			.spec(requestSpec(userCredentials))
		.when()
			.post("login")
		.then()
			.spec(responseSpec_OK())
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response_schema/LoginResponseScema.json"));
	}

}
