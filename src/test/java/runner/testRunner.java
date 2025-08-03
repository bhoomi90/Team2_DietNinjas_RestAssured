package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		
		
		features = {"src\\test\\resources\\features\\01UserLogin\\01userLogin.feature",
				    "src\\test\\resources\\features\\02POST_createDietician.feature",
				      "src\\test\\resources\\features\\03DieticianLogin\\DieticianLogin.feature",
				     //"src\\test\\resources\\features\\userLogout.feature"
				},
	    glue = "stepDefinitions",
	    plugin = {"pretty", "html:target/cucumber-report.html",
	    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},// Allure plugin for cucumber v7
	    // tags = " @test or @CreateDietician or @dieticianvalidlogin",
	    monochrome = true
		)


public class testRunner extends AbstractTestNGCucumberTests {
	
}