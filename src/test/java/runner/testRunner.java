package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
	    plugin = {
	        "pretty",
	        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
	        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	        "json:target/CucumberReports/DieticianApiJson.json",
	        "html:target/CucumberReports/DieticianApi.html",
	     //   "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
	    },
	    monochrome = false,
	  //tags = "@dieticianvalidlogin",
	    features = {"src\\test\\resources\\features"},
	    glue = {"stepDefinitions"}
	)
public class testRunner extends AbstractTestNGCucumberTests {
	
}

