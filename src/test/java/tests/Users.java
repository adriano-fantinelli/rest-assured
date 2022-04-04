package tests;

import io.qameta.allure.*;
import model.UsersModel;
import org.testng.annotations.Test;
import requests.UsersRequests;
import utils.BaseTest;

import static controller.UsersController.*;
import static controller.UsersController.putUserSuccess;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Feature("Users")
public class Users extends BaseTest {

    UsersRequests usersRequests = new UsersRequests();
    UsersModel postUserSuccess = postUserSuccess();
    UsersModel putUserSuccess = putUserSuccess();

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
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .body(
                        "id", equalTo(postUserSuccess.getId()),
                        "userName", equalTo(postUserSuccess.getUserName()),
                        "password", equalTo(postUserSuccess.getPassword())
                )
                .statusCode(200)
                .extract()
                .path("id");
        usersRequests.deleteUsers(idResponse);
    }

    @Test
    @Story("Edit an user")
    public void editUser() {
        Integer idResponse = usersRequests.postUsers(postUserSuccess)
                .then()
                .extract()
                .path("id");
        usersRequests.putUsers(putUserSuccess, idResponse)
                .then()
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .body(
                        "id", equalTo(putUserSuccess().getId()),
                        "userName", equalTo(putUserSuccess.getUserName()),
                        "password", equalTo(putUserSuccess.getPassword())
                )
                .statusCode(200);
        usersRequests.deleteUsers(idResponse);
    }

    @Test
    @Story("Delete an user")
    public void deleteUser() {
        usersRequests.deleteUsers(idExistente())
                .then()
                .statusCode(200);
    }
}
