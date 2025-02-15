package org.automation.tests;

import org.automation.utils.APIUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.automation.apis.Endpoints;
import org.automation.pojo.request.OrderRequest;
import org.automation.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Test class for verifying the POST /orders endpoint.
 * This test ensures that an order can be successfully created.
 */
public class PostOrderTest {

    // Static block to set the base URI for the API
    static {
        RestAssured.baseURI = Endpoints.BASE_URL;  // Set the base URI for the API
    }

    /**
     * Test method to verify the POST /orders endpoint.
     * Steps:
     * 1. Register a client with a random email and fixed client name.
     * 2. Create an OrderRequest POJO with book ID and customer name.
     * 3. Send a POST request to create the order.
     * 4. Verify the response status code is 201 (Created).
     * 5. Validate that the response contains an order ID.
     */
    @Test
    public void testPostOrder() {
        // Generate a random email and use a fixed client name "Ammar"
        String email = Helper.getRandomEmail();
        String clientName = "Ammar";

        // Register the client with random email and fixed client name, then get the Bearer token
        String token = APIUtils.getTokenAFterClientRegistration(clientName, email);

        // Create OrderRequest POJO and set the request data
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setBookId(1);  // Example book ID
        orderRequest.setCustomerName("John");  // Example customer name

        // Send POST request to /orders with the authentication token
        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)  // Bearer token for authentication
                .body(orderRequest)  // Request body with order details
                .when()
                .post(Endpoints.ORDERS_URL)  // Endpoint to create an order
                .then()
                .statusCode(201)  // HTTP Status Code 201 - Created (assuming successful order creation)
                .extract().response();

        // Validate the response
        Assert.assertNotNull(response.jsonPath().getString("orderId"), "Order ID should be returned.");
        System.out.println("Order ID: " + response.jsonPath().getString("orderId"));  // Print the order ID for debugging
    }
}
