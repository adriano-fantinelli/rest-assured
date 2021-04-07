package tests;

import data.DataUsers;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

@Feature("Users")
public class Users {

    @BeforeClass
    public static void base(){
        baseURI = "https://fakerestapi.azurewebsites.net/api/v1/Users";
    }

    @Test
    @Story("Search all users")
    public void searchAllUsers() {
        given()
                .when()
                .get("")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/users-schema.json"))
                .log().all();
    }

    @Test
    @Story("Search an user")
    public void searchUser(){
        given()
                .when()
                .get("9")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(9))
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .log().all();
    }

    @Test
    @Story("Create an user")
    public void createUser(){
        DataUsers dataUsers = new DataUsers();
        given()
                .contentType("application/json")
                .body(dataUsers.getNewUser())
                .when()
                .post()
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .log().all();
    }

    @Test
    @Story("Edit an user")
    public void editUser(){
        DataUsers dataUsers = new DataUsers();
        given()
                .contentType("application/json")
                .body(dataUsers.getEditedUser())
                .when()
                .put("10")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/user-schema.json"))
                .log().all();
    }

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
