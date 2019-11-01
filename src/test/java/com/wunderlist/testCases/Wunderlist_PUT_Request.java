package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class Wunderlist_PUT_Request extends testBase {
	String titleName = "gmen";

	@BeforeClass
	public void putRequest() {
		Initialize();

	}

	@Test
	void putListWithRevison() {
		final ExtentTest test = extent.createTest("Put Request", "Update List");
		logger.info("***** Verifying PUT Request List With Revision *****");
		test.log(Status.INFO, "PUT Request  With Revision");
		final Response getResponse = httpRequest.request(Method.GET, "/lists/" + Id);
		final int revision = getResponse.jsonPath().getInt("revision");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", titleName);
		requestParams.put("revision", revision);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.PUT, "/lists/" + Id);
		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

	@Test
	void putListWithInvalidRevision() {
		final ExtentTest test = extent.createTest("Put Request 2", "Fail to update");
		logger.info("***** Verifying PUT Request List With Invalid Revision  *****");
		test.log(Status.INFO, "PUT Request  With invalid Revision");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", titleName);
		requestParams.put("revision", -1);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.PUT, "/lists/" + Id);
		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 409);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

}
