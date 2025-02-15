package org.automation.tests;

import org.automation.apis.Endpoints;
import org.automation.utils.APIUtils;
import org.automation.utils.Helper;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

/**
 * Test class for verifying the DELETE /orders endpoint.
 * This test ensures that an order can be successfully deleted.
 */
public class DeleteOrderTest {

    // Static block to set the base URI for the API
    static {
        RestAssured.baseURI = Endpoints.BASE_URL;  // Set the base URI for the API
    }

    /**
     * Test method to verify the DELETE /orders/{orderId} endpoint.
     * Steps:
     * 1. Register a client with a random email and fixed client name.
     * 2. Create an order and retrieve the orderId.
     * 3. Send a DELETE request to delete the order.
     * 4. Verify the response status code is 204 (No Content).
     */
    @Test
    public void testDeleteOrder() {
        // Generate a random email and use a fixed client name "Ammar"
        String email = Helper.getRandomEmail();
        String clientName = "Ammar";

        // Register the client with random email and fixed client name, then get the Bearer token
        String token = APIUtils.getTokenAFterClientRegistration(clientName, email);

        // Create an order and retrieve the orderId
        String orderId = APIUtils.getOrderIDAfterCreatingOrder(token, 1, "John");

        // Send DELETE request to /orders with the orderId
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)  // Bearer token for authentication
                .when()
                .delete(Endpoints.ORDERS_URL + "/" + orderId)  // Using the orderId from the created order
                .then()
                .statusCode(204)  // HTTP Status Code 204 - No Content (assuming successful deletion)
                .extract()
                .response();
    }
}