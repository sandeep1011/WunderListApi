package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class Wunderlist_PUT_Request extends testBase {
	String titleName = "gmen";
	int putRevision = 4;

	@BeforeClass
	public void putRequest() {
		Initialize();

	}

	@Test
	void putList() {
		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", titleName);
		requestParams.put("revision", putRevision);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.PUT, "/lists/" + Id);
		final int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

}
