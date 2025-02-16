package org.automation.tests;

import org.automation.BaseClass;
import org.automation.apis.Endpoints;
import org.automation.utils.APIUtils;
import org.automation.utils.Helper;  // Assuming Helper class has the getRandomEmail() method
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * Test class for verifying the PATCH /orders/{orderId} endpoint.
 * This test ensures that an order can be successfully updated.
 */
public class PatchOrderTest extends BaseClass {

    // Static block to set the base URI for the API
    static {
        RestAssured.baseURI = Endpoints.BASE_URL;  // Set the base URI for the API
    }

    /**
     * Test method to verify the PATCH /orders/{orderId} endpoint.
     * Steps:
     * 1. Register a client with a random email and fixed client name.
     * 2. Create an order and retrieve the orderId.
     * 3. Send a PATCH request to update the order with a new customer name.
     * 4. Verify the response status code is 204 (No Content).
     */
    @Test(dataProvider = "getData", priority = 0)
    public void testUpdateOrder(HashMap<String, String> data) {
        // Generate a random email and use a fixed client name "Ammar"
        String email = Helper.getRandomEmail();
        String customerName = data.get("customerName");
        String clientName = data.get("clientName");

        // Register the client with random email and fixed client name, then get the Bearer token
        String token = APIUtils.getTokenAFterClientRegistration(clientName, email);

        // Create an order and retrieve the orderId
        String orderId = APIUtils.getOrderIDAfterCreatingOrder(token, 1, customerName);

        // Update the order with a new customer name
        String updatedCustomerName = "Michael";  // New customer name for the order update
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)  // Bearer token for authentication
                .body("{\"customerName\": \"" + updatedCustomerName + "\"}")  // Request body with updated customer name
                .when()
                .patch(Endpoints.ORDERS_URL + "/" + orderId)  // Using the orderId from the created order
                .then()
                .statusCode(204)  // HTTP Status Code 204 - No Content (assuming successful update)
                .extract()
                .response();
    }
}
