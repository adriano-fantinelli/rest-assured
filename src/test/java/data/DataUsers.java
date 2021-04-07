package data;

public class DataUsers {

    String newUser = "{\n" +
            "  \"id\": 31,\n" +
            "  \"userName\": \"Adriano\",\n" +
            "  \"password\": \"teste123\"\n" +
            "}";

    String editedUser = "{\n" +
            "  \"id\": 31,\n" +
            "  \"userName\": \"Adriano edited\",\n" +
            "  \"password\": \"teste123 edited\"\n" +
            "}";

    public String getNewUser(){
        return newUser;
    }

    public String getEditedUser(){
        return editedUser;
    }
}
