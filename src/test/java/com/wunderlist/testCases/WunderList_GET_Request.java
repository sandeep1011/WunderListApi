package com.wunderlist.testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.wunderlist.base.testBase;

import io.restassured.http.Method;

public class WunderList_GET_Request extends testBase {

	@BeforeClass
	public void getRequest() throws InterruptedException {

		logger.info("*****Started testcase OF List*****");
		Initialize();

	}

	@Test
	void verifyGETRequest() {
		final ExtentTest test = extent.createTest("Get Request", "GET List");
		logger.info("*****Verifying GET Request*****");
		test.log(Status.INFO, "Verifying GET Request");
		response = httpRequest.request(Method.GET, "/lists");

		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
		test.log(Status.PASS, "Status Code : " + statusCode);

	}

	@Test
	void verifyGETRequestByID() {
		final ExtentTest test = extent.createTest("GET Request by ID", "GET List By ID");
		logger.info("*****Verifying GET Request By ID*****");
		test.log(Status.INFO, "Verifying GET Request By ID");
		response = httpRequest.request(Method.GET, "/lists/" + Id);
		final int statusCode = response.getStatusCode();
		logger.info("Status Code==> " + statusCode);
		Assert.assertEquals(statusCode, 200);
		test.log(Status.PASS, "Status Code : " + statusCode);

	}

}
