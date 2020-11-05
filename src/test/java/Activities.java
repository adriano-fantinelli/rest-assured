import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Activities {
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
                .body("{" +
                        "  \"ID\": 31,\n" +
                        "  \"Title\": \"Play soccer\",\n" +
                        "  \"DueDate\": \"2020-10-30T10:26:17.362Z\",\n" +
                        "  \"Completed\": false\n" +
                        "}")
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
                .body("{" +
                        "  \"ID\": 10,\n" +
                        "  \"Title\": \"Edited Activity\",\n" +
                        "  \"DueDate\": \"2020-11-05T10:26:17.362Z\",\n" +
                        "  \"Completed\": true\n" +
                        "}")
        .when()
                .put("10")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/activities/activity-schema.json"))
                .log().all();
    }
}
