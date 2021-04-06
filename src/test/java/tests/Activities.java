package tests;

import data.DataActivities;
import io.qameta.allure.*;
import org.junit.*;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Feature("Activities")
public class Activities {

    @BeforeClass
    public static void base() {
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Activities";
    }

    @Test
    @Story("Search all activities")
    public void searchAllActivities() {
        given()
                .when()
                .get("")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activities-schema.json"))
                .log().all();
    }

    @Test
    @Story("Create an activity")
    public void createActivity() {
        DataActivities dataActivities = new DataActivities();
        given()
                .contentType("application/json")
                .body(dataActivities.getNewActivity())
                .when()
                .post("")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }

    @Test
    @Story("Delete an activity")
    public void deleteActivity() {
        given()
                .when()
                .delete("31")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    @Story("Search an activity")
    public void searchActivity() {
        given()
                .when()
                .get("10")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }

    @Test
    @Story("Edit an activity")
    public void editActivity() {
        DataActivities dataActivities = new data.DataActivities();
        given()
                .contentType("application/json")
                .body(dataActivities.getEditedActivity())
                .when()
                .put("10")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }
}
