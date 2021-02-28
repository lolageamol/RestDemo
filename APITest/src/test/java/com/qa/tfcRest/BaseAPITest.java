package com.qa.tfcRest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPITest {

	public final String BASE_URL = "https://reqres.in";
	public final String USERNAME = "Auto1Admin";
	public final String PASSWORD = "ionadmin1234";

	public static RequestSpecBuilder requestSpecBuilder;
	ResponseSpecBuilder responseSpecBuilder;
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	public static Response response;
	ObjectMapper objectMapper = new ObjectMapper();
	
	public Logger logger; 

	@BeforeClass
	public void setup() {
		
		logger = Logger.getLogger("APITest");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
		
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = BASE_URL + "/api";

		PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
		basicAuth.setUserName(USERNAME);
		basicAuth.setPassword(PASSWORD);

		//RestAssured.authentication = basicAuth;
		
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		requestSpecBuilder = new RequestSpecBuilder().setAuth(basicAuth);
		requestSpecBuilder.setAccept(ContentType.JSON);
		//requestSpecBuilder.setPort(443);
		//requestSpecBuilder.log(LogDetail.ALL);
		requestSpec = requestSpecBuilder.build();
		//requestSpec.queryParam("rigMode", "AssayDev");
		
		responseSpecBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL);
		responseSpec = responseSpecBuilder.build();
		
	}

	public RequestSpecification given(RequestSpecification requestSpec) {
		return RestAssured.given(requestSpec);
	}
}
