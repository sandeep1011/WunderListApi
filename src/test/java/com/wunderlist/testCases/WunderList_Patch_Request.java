package com.wunderlist.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wunderlist.base.testBase;

import io.restassured.http.Method;
import io.restassured.response.Response;

public class WunderList_Patch_Request extends testBase {
	String titleName = "rthj";

	@BeforeClass
	public void patchRequest() {
		Initialize();

	}

	@Test
	void patchList() {

		final Response getResponse = httpRequest.request(Method.GET, "/lists/" + Id);
		final int revision = getResponse.jsonPath().getInt("revision");

		final JSONObject requestParams = new JSONObject();
		requestParams.put("title", titleName);
		requestParams.put("revision", revision);
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		final Response response = httpRequest.request(Method.PATCH, "/lists/" + Id);

		final int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

}