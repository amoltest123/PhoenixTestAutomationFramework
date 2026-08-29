package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojos.CreateJobPayload;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problems;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Amol", "Latthe", "9960373464", "", "amollattheict@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur Nagar", "Inorbit", "Mumbai", "411039", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2026-06-23T18:30:00.000Z", "77986587299749", "77986587299749", "77986587299749", "2026-06-23T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problems);
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsList);
		
		given()
			.spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(SpecUtils.responseSpec_OK())
			.body(matchesJsonSchemaInClasspath("response_schema/CreateJobAPIResponseSchema.json"))
			.body("message", equalTo("Job created successfully. "))
			.body("data.mst_service_location_id", equalTo(1))
			.body("data.job_number", startsWith("JOB_"));
	}
}
