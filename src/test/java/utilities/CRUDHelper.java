package utilities;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class CRUDHelper {

    public static RequestSpecification getRequest() {
        RestAssured.baseURI = ConfigReader.getBaseURI();
        RequestSpecification request = RestAssured.given().auth().basic(ConfigReader.getAuthUserName(), ConfigReader.getAuthPassword());
        return request;
    }

}
