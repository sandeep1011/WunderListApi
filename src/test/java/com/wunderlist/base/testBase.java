package com.wunderlist.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testBase {

	public static RequestSpecification httpRequest;
	public static Response response;
	public String Id = "408410690";
	public Logger logger;
	public static ExtentReports extent = new ExtentReports();
	public static ExtentHtmlReporter htmlReporter;

	public static ExtentTest test;

	@BeforeSuite
	public void reportSetup() {

		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void reportTeardown() {
		extent.flush();
	}

	public void Initialize() {
		RestAssured.baseURI = "https://a.wunderlist.com/api/v1";

		httpRequest = RestAssured.given();
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("X-Access-Token", "db6a52d93311142c299bf9c9c5bc551bef65ee4bc6a81dcda3fdc30a6f5e");
		httpRequest.header("X-Client-ID", "5a47edd70054587a8647");
	}

	@BeforeClass

	public void setup() {
		logger = Logger.getLogger("WunderListAPI");
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);

	}

}
