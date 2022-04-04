package utils;

import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.baseURI;

public class BaseTest {
    @BeforeTest
    public static void setup() {
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1/";
    }
}
