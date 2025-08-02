package utilities;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;


public class CRUDHelper {

    public static RequestSpecification getRequestWithNoAuth() {

        RestAssured.baseURI = configReader.getProperty ("baseURL");
        RequestSpecification request = RestAssured.given();
        return request;
    }

    public static RequestSpecification getRequestWithPatientToken(){

        return;
    }
}
