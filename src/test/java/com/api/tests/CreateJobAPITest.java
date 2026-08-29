package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojos.CreateJobPayload;
import com.api.pojos.Customer;
import com.api.pojos.CustomerAddress;
import com.api.pojos.CustomerProduct;
import com.api.pojos.Problems;
import com.api.utils.SpecUtils;

public class CreateJobAPITest {

	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Amol", "Latthe", "9960373464", "", "amollattheict@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant Galaxy", "Bangur Nagar", "Inorbit", "Mumbai", "411039", "India", "Maharashtra");
		CustomerProduct customerProduct = new CustomerProduct("2026-06-23T18:30:00.000Z", "10586587299749", "10586587299749", "10586587299749", "2026-06-23T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Battery issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0] = problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given()
			.spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
			.post("/job/create")
		.then()
			.spec(SpecUtils.responseSpec_OK());
	}
}
