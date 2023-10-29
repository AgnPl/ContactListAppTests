package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogOutUserTest {

    public static final String LOGOUT_POST_API = "https://thinking-tester-contact-list.herokuapp.com/users/logout";

    @Test
    public void logOutUserTest() {
        GettingBearerToken gettingBearerToken = new GettingBearerToken();
        String bearerToken = gettingBearerToken.gettingBearerToken();

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .post(LOGOUT_POST_API);

        response.then().statusCode(200);
        Assertions.assertEquals(200, response.getStatusCode(), "Response code is not as expected.");
    }
}
