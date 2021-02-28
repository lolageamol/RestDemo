package com.qa.tfcRest;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tfcRest.pojos.User;

import groovy.transform.TimedInterrupt;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestPlanRunsAPI extends BaseAPITest {

	@Test
	@Ignore
	public void getPlannedRunInfoTest() {
		/*
		 * requestSpec = requestSpecBuilder.setBasePath("/getPlannedRunById")
		 * .addQueryParam("apiKey", "6956497e3a7940737068454b6e")
		 * .addQueryParam("rigName", "PurificationTestRig") .addQueryParam("rigType",
		 * "Purification") .addQueryParam("planShortCodeId", "AD0BB")
		 * .addQueryParam("planRunId", "424") .build();
		 */
		logger.info("test start");
		requestSpec.queryParam("apiKey", "6956497e3a7940737068454b6e");
		requestSpec.queryParam("rigName", "PurificationTestRig");
		requestSpec.queryParam("rigType", "Purification");
		requestSpec.queryParam("planShortCodeId", "AD0BB");
		requestSpec.queryParam("planRunId", "424");
		requestSpec.basePath("/getPlannedRunById");

		given(requestSpec).when().get().then().spec(responseSpec);

		Response response = requestSpec.request(Method.GET);
		response.getStatusCode();
		response.getStatusLine();
		response.getHeaders();
		response.getBody();
		response.getTimeIn(TimeUnit.SECONDS);
		logger.info("test complete");

	}

	@Test
	public void getUsers() {
		
		response = given(requestSpec).request(Method.GET, "/users?page={pageNumber}", 2);
		response.then().body("page", equalTo(1));
	}
	
	@Test(invocationTimeOut = 5000, invocationCount = 10)
	public void createUser() throws JsonProcessingException {
		
		User user1 = new User("Amol" , "QA");
		ObjectMapper om = new ObjectMapper();
		
		
		requestSpec.body(om.writeValueAsString(user1));
		response = given(requestSpec).request(Method.POST, "/users");
		response.prettyPrint();
		response.getTimeIn(TimeUnit.MILLISECONDS);
		User actualUser = response.as(User.class);
		//Assert.assertEquals( user1.getName() ,actualUser.getName());
		Assert.assertNotNull(actualUser.getCreatedAt());
	}

}
