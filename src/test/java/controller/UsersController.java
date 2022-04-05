package test.java.controller;

import test.java.model.UsersModel;

public class UsersController {

    public static int idExistente() { return 10; }

    public static int idInexistente() { return 1000; }

    public static String notFound() { return "Not Found"; }

    public static UsersModel postUserSuccess () {
        return new UsersModel(
                30,
                "Post test",
                "Password"
        );
    }

    public static UsersModel putUserSuccess () {
        return new UsersModel(
                30,
                "Put test",
                "Password1"
        );
    }
}
