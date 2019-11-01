package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WunderList_DELETE_Request extends testBase {

	@BeforeClass
	public void deleteRequest() {
		Initialize();

	}

	@Test(priority = 1)
	void deleteListWithRevison() {
		response = httpRequest.request(Method.GET, "/lists");
		final JsonPath i = response.jsonPath();
		final int getID = i.get("[6].id");

		final ExtentTest test = extent.createTest("DELETE Request", "Delete List");
		logger.info("*****Verifying DELETE Request With Revision*****");
		test.log(Status.INFO, "Verifying DELETE Request");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("revision", 1);
		httpRequest.queryParams(requestParams);
		httpRequest.pathParam("id", getID);

		final Response response = httpRequest.request(Method.DELETE, "/lists/{id}");

		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 204);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

	@Test(priority = 2)
	void deleteListWithInvalidRevison() {

		final ExtentTest test = extent.createTest("DELETE Request", "Delete List With Invalid Revision");
		logger.info("*****Verifying DELETE Request With Invalid Revision*****");
		test.log(Status.INFO, "Verifying DELETE Request with invalid revision");
		final JSONObject requestParams = new JSONObject();
		requestParams.put("revision", 0);
		httpRequest.queryParams(requestParams);
		httpRequest.pathParam("id", Id);

		final Response response = httpRequest.request(Method.DELETE, "/lists/{id}");

		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 409);
		test.log(Status.PASS, "Status Code : " + statusCode);
	}

}
