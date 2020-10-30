import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class Activities {

    @Test
    public void permissionApi(){
        given()
            .contentType("application/json")
            .body("{\"email\": \"adrianofa2013@gmail.com\", \"appDescription\": \"Adriano Test\"}")
        .when()
            .post("https://api.thecatapi.com/v1/user/passwordlesssignup")
        .then()
            .statusCode(200)
            .body("message", containsString("SUCCESS"))
            .log().all();
    }


    @Test
    public void searchAllActivities(){
        given()
                .when().get("http://fakerestapi.azurewebsites.net/api/Activities")
                .then()
                    .log().all();
    }

    @Test
    public void createActivity(){
        given()
                .contentType("application/json")
                .body("{" +
                        "  \"ID\": 11,\n" +
                        "  \"Title\": \"Play soccer\",\n" +
                        "  \"DueDate\": \"2020-10-30T10:26:17.362Z\",\n" +
                        "  \"Completed\": false\n" +
                        "}")
        .when()
                .post("http://fakerestapi.azurewebsites.net/api/Activities")
        .then()
                .statusCode(200)
//                .body("", containsString(""))
                .log().all();
    }

    @Test
    public void deleteActivity(){
        given().when().post("url").then();
    }

    @Test
    public void searchActivity(){
        given().when().post("url").then();
    }

    @Test
    public void editActivity(){
        given().when().post("url").then();
    }

}
