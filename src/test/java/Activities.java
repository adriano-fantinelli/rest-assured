import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Activities extends dataActivities {
    @BeforeClass
    public static void base(){
        baseURI = "http://fakerestapi.azurewebsites.net/api/Activities/";
    }

    @Test
    public void searchAllActivities(){
        given()
        .when()
                .get("")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activities-schema.json"))
                .log().all();
    }

    @Test
    public void createActivity(){
        given()
                .contentType("application/json")
                .body(newActivity)
        .when()
                .post("")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }

    @Test
    public void deleteActivity(){
        given()
        .when()
                .delete("31")
        .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void searchActivity(){
        given()
        .when()
                .get("10")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }

    @Test
    public void editActivity(){
        given()
                .contentType("application/json")
                .body(editedActivity)
        .when()
                .put("10")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }
}
