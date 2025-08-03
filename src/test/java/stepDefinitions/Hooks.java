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

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public static String baseUrl;
    public static TestCasesWrapper allTestData;
    public static TestCaseData currentLoginTest;
    public static String token;
    public static RequestSpecification request;

    @Before(order = 0)
    public void setup() {
        // Load base URL from config
        baseUrl = configReader.getProperty("baseURL");
        logger.info("Base URL loaded: " + baseUrl);

        // Load all test data from JSON
        allTestData = JSONDataReader.readAllModules("src/test/resources/testData/jsonData.json");
        logger.info("All test data loaded");

        // Optionally, load a specific test case if required globally
        currentLoginTest = JSONDataReader.getTestCaseById(allTestData.getLoginTests(), "LT_001");
        logger.info("Loaded Login Test Case: " + currentLoginTest.getScenario());
        
    }

    @After
    public void tearDown() {
        logger.info("Cleaning up after scenario...");
        // Add cleanup steps if needed 
    }
}

