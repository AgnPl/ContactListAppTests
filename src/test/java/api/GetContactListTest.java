package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetContactListTest {

    public static final String GET_CONTACT_API = "https://thinking-tester-contact-list.herokuapp.com/contacts";

    @Test
    public void getContactListTest() {
        GettingBearerToken gettingBearerToken = new GettingBearerToken();
        String bearerToken = gettingBearerToken.gettingBearerToken();

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .get(GET_CONTACT_API);

        response.then().statusCode(200);
        Assertions.assertEquals(200, response.getStatusCode(), "Response code is not as expected.");
    }

    @Test
    public void getContactListTestWithoutBearerToken() {
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + "")
                .header("Content-Type", "application/json")
                .get(GET_CONTACT_API);

        response.then().statusCode(401);
        Assertions.assertEquals(401, response.getStatusCode(), "Response code is not as expected.");
    }
}
