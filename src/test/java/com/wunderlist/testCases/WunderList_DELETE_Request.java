package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class WunderList_DELETE_Request extends testBase {
	int putRevision = 1;

	@BeforeClass
	public void deleteRequest() {
		Initialize();

	}

	@Test
	void deleteList() {
		final JSONObject requestParams = new JSONObject();

		requestParams.put("revision", putRevision);
		httpRequest.body(requestParams.toJSONString());
		httpRequest.header("Content-Type", "application/json");

		final Response response = httpRequest.request(Method.DELETE, "/lists/" + Id);
		final int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 204);
	}

}
