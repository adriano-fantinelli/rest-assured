package test.java.tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.model.UsersModel;
import test.java.requests.UsersRequests;
import test.java.utils.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static test.java.controller.UsersController.*;

@Feature("Users")
public class Users extends BaseTest {

    @DataProvider(name = "invalid")
    public Object[][] invalid() {
        return new Object[][]{
                {nonexistentId(), notFound()}
        };
    }

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
    @Story("Search an existent user")
    public void searchExistentUser() {
        usersRequests.getUsersId(existentId())
                .then()
                .assertThat()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .body("id", equalTo(existentId()));
    }

    @Test(dataProvider = "invalid")
    @Story("Search a nonexistent user")
    public void searchNonexistentUser(int id, String title) {
        usersRequests.getUsersId(id)
                .then()
                .assertThat()
                .statusCode(404)
                .body("title", equalTo(title));
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
        usersRequests.deleteUsers(existentId())
                .then()
                .statusCode(200);
    }
}
