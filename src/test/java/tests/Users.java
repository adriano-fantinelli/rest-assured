package tests;

import controller.UsersController;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import model.UsersModel;
import org.testng.annotations.Test;
import requests.UsersRequests;

import static controller.UsersController.*;
import static controller.UsersController.idExistente;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Feature("Users")
public class Users {

    UsersRequests usersRequests = new UsersRequests();
    UsersModel postUserSuccess = postUserSuccess();

    @Test
    @Story("Search all users")
    public void searchAllUsers() {
        usersRequests.getUsers()
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/users-schema.json"));
    }

    @Test
    @Story("Search an user")
    public void searchUser() {
        usersRequests.getUsersId(idExistente())
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .body("id", equalTo(idExistente()));
    }

    @Test
    @Story("Create an user")
    public void createUser() {
       Integer idResponse = usersRequests.postUsers(postUserSuccess)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .body(
                        "id", equalTo(postUserSuccess.getId()),
                        "userName", equalTo(postUserSuccess.getUserName()),
                        "password", equalTo(postUserSuccess.getPassword())
                )
               .extract()
               .path("id");
        usersRequests.deleteUsers(idResponse);
    }

//    @Test
//    @Story("Edit an user")
//    public void editUser() {
//        UsersController dataUsers = new UsersController();
//        given()
//                .contentType("application/json")
//                .body(dataUsers.getEditedUser())
//                .when()
//                .put("10")
//                .then()
//                .statusCode(200)
//                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
//                .log().all();
//    }

    @Test
    @Story("Delete an user")
    public void deleteUser() {
        given()
                .when()
                .delete("31")
                .then()
                .statusCode(200)
                .log().all();
    }
}
