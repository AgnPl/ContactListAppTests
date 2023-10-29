package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddContactTest {

    public static final String POST_ADD_CONTACT_API = "https://thinking-tester-contact-list.herokuapp.com/contacts";

    private String bearerToken;

    @BeforeEach
    public void setUp() {
        GettingBearerToken gettingBearerToken = new GettingBearerToken();
        bearerToken = gettingBearerToken.gettingBearerToken();
    }

    @Test
    public void addContactTest() {

        String requestBody = """
                {
                    "firstName": "John",
                    "lastName": "Doe",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(POST_ADD_CONTACT_API);

        response.then().statusCode(201);
        Assertions.assertEquals(201, response.getStatusCode(), "Response code is not as expected.");
    }

    @Test
    public void addContactTestWithoutRequiredFields() {

        String requestBody = """
                {
                    "firstName": "",
                    "lastName": "",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(POST_ADD_CONTACT_API);

        response.then().statusCode(400);
        Assertions.assertEquals(400, response.getStatusCode(), "Response code is not as expected.");
    }

    @Test
    public void addContactTestWithoutBearerToken() {
        String requestBody = """
                {
                    "firstName": "Mike",
                    "lastName": "Wazowsky",
                    "birthdate": "1970-01-01",
                    "email": "jdoe@fake.com",
                    "phone": "8005555555",
                    "street1": "1 Main St.",
                    "street2": "Apartment A",
                    "city": "Anytown",
                    "stateProvince": "KS",
                    "postalCode": "12345",
                    "country": "USA"
                }""";

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + "")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post(POST_ADD_CONTACT_API);

        response.then().statusCode(401);
        Assertions.assertEquals(401, response.getStatusCode(), "Response code is not as expected.");
    }
}
