package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddUserTest {

    public static final String POST_ADD_USER_API = "https://thinking-tester-contact-list.herokuapp.com/users";

    @Test
    public void apiTest_AddUser() {
        //This test will be executed once per db clean, because emails can't repeat themselves
        GettingBearerToken gettingBearerToken = new GettingBearerToken();
        String bearerToken = gettingBearerToken.gettingBearerToken();

        String requestBody = """
                 {
                   "firstName": "Test121",
                   "lastName": "Banana",
                   "email": "banana13@randomhost.com",
                   "password": "1121myPassword"
                 }""";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(POST_ADD_USER_API);

        response.then().statusCode(201);
        Assertions.assertEquals(201, response.getStatusCode(), "Response code is not as expected.");
    }
}
