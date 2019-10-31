package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class POST_Request extends testBase {
	String titleName = "xmen";

	@BeforeClass
	public void postRequest() {
		Initialize();

	}

	@Test
	void createListSuccess() {
		logger.info("***** Verifying Create List *****");
		test.log(Status.INFO, "Create List Success");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", titleName);
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.POST, "/lists");
		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 201);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

	@Test
	void createListWithInvalidTitle() {
		logger.info("***** Create List With Invalid Title*****");
		test.log(Status.INFO, "Fail in Creating List with Empty String in Title");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", "");
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.POST, "/lists");
		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 422);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

}
