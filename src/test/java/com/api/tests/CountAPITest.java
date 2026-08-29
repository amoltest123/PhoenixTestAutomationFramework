package com.api.tests;

import static com.api.constant.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() {
		
		given()
			.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtils.responseSpec_OK())
			.body("message", equalTo("Success"))
			.time(lessThan(1000L))
			.body("data", notNullValue())
			.body("data.size()", equalTo(3))
			.body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label", everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_fst_assignment", "created_today", "pending_for_delivery"))
			.body(matchesJsonSchemaInClasspath("response_schema/CountAPIResponseSchema-FD.json"));
	}
	
	@Test
	public void countAPITest_MissingAuthToken() {
		
		given()
			.spec(SpecUtils.requestSpec())
		.when()
			.get("/dashboard/count")
		.then()
			.spec(SpecUtils.responseSpec_Text(401));
	}
}
