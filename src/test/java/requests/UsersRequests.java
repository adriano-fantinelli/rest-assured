package requests;

import io.restassured.response.Response;
import model.UsersModel;

import static io.restassured.RestAssured.given;

public class UsersRequests {

    String users = "/Users";

    public Response getUsers() {
        return given()
                .when()
                .get("/Users");
    }

    public Response getUsersId(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(users + "/{id}");
    }

    public Response postUsers(UsersModel user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post(users);
    }

    public Response putUsers(UsersModel user, int id) {
        return given()
                .contentType("application/json")
                .pathParam("id", id)
                .body(user)
                .when()
                .put(users + "/{id}");
    }

    public Response deleteUsers(int id) {
        return given()
                .pathParam("id", id)
                .delete(users + "/{id}");
    }
}
