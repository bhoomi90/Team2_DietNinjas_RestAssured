package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		
		features = {"src\\test\\resources\\features"},
	    glue = "stepDefinitions",
	    plugin = {"pretty", "html:target/cucumber-report.html",
	    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},// Allure plugin for cucumber v7
	     //tags = " @test4", 
	    monochrome = true
		)


public class testRunner extends AbstractTestNGCucumberTests {

}
