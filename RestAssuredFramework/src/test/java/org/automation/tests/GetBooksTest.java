package org.automation.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.apis.Endpoints;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class for verifying the GET /books endpoint.
 * This test ensures that the API returns a list of books successfully.
 */
public class GetBooksTest {

    // Static block to set the base URI for the API
    static {
        RestAssured.baseURI = Endpoints.BASE_URL;  // Set the base URI for the API
    }

    /**
     * Test method to verify the GET /books endpoint.
     * Steps:
     * 1. Send a GET request to fetch the list of books.
     * 2. Verify the response status code is 200 (OK).
     * 3. Validate that the response contains a non-empty list of books.
     */
    @Test
    public void testGetBooks() {
        // Send GET request to fetch list of books
        Response response = given()
                .when()
                .get(Endpoints.BOOKS_URL)  // Endpoint to fetch books
                .then()
                .statusCode(200)  // Expecting a successful response with status code 200
                .extract().response();

        // Validate that the response contains a list of books
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Books list should not be empty.");
        System.out.println("Books List: " + response.body().asString());  // Print the list of books for debugging
    }
}
