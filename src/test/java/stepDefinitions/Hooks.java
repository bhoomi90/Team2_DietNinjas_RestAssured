package stepDefinitions;

import io.cucumber.java.Before;
import io.restassured.specification.RequestSpecification;
import pojo.TestCaseData;
import pojo.TestCasesWrapper;
import io.cucumber.java.After;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.configReader;
import utilities.JSONDataReader;
import utilities.LoggerLoad;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public static String baseUrl;
    public static TestCasesWrapper allTestData;
    public static TestCaseData currentLoginTest;
    public static String token;
    public static RequestSpecification request;
    public static String dieticianId;       //<-- save id and loginpassword
    public static String dieticianLoginPwd; 
    public static String dieticianEmail;


    @Before(order = 0)
    public void setup() {
        // Load base URL from config
        baseUrl = configReader.getProperty("baseURL");
        LoggerLoad.info("Base URL loaded: " + baseUrl);

        // Load all test data from JSON
        allTestData = JSONDataReader.readAllModules("src/test/resources/testData/jsonData.json");
        LoggerLoad.info("All test data loaded");

    	
    }

    @After
    public void tearDown() {
        logger.info("Cleaning up after scenario...");
        // Add cleanup steps if needed 
    }
}

