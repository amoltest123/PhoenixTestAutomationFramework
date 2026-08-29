package com.api.utils;

import org.hamcrest.Matchers;

import com.api.constant.Role;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {

	public static RequestSpecification requestSpec() {
		RequestSpecification request =	new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	public static RequestSpecification requestSpec(Object payload) {
		RequestSpecification request =	new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.setBody(payload)
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	public static RequestSpecification requestSpecWithAuth(Role role) {
		RequestSpecification request =	new RequestSpecBuilder()
		.setBaseUri(ConfigManager.getProperty("BASE_URI"))
		.setContentType(ContentType.JSON)
		.setAccept(ContentType.JSON)
		.addHeader("Authorization", AuthTokenProvider.getToken(role))
		.log(LogDetail.URI)
		.log(LogDetail.METHOD)
		.log(LogDetail.HEADERS)
		.log(LogDetail.BODY)
		.build();
		return request;
	}
	
	public static ResponseSpecification responseSpec_OK() {
	 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(Matchers.lessThan(2000L))
		.log(LogDetail.ALL)
		.build();
	 return responseSpecification;
	}
	
	public static ResponseSpecification responseSpec_Json(int statusCode) {
		 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(statusCode)
			.expectResponseTime(Matchers.lessThan(2000L))
			.log(LogDetail.ALL)
			.build();
		 return responseSpecification;
		}
	
	public static ResponseSpecification responseSpec_Text(int statusCode) {
		 ResponseSpecification responseSpecification = new ResponseSpecBuilder()
			.expectStatusCode(statusCode)
			.expectResponseTime(Matchers.lessThan(2000L))
			.log(LogDetail.ALL)
			.build();
		 return responseSpecification;
		}
}
