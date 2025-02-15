package org.automation.utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.automation.apis.Endpoints;
import org.automation.pojo.request.ClientRegistrationRequest;
import org.automation.pojo.request.OrderRequest;
import static io.restassured.RestAssured.given;

public class APIUtils {
    /**
     * Logs in to the API and returns the Bearer token.
     *
     * @param username The username for login.
     * @param password The password for login.
     * @return The Bearer token as a String.
     */
    /**
     * Registers a new API client and returns the Bearer token.
     *
     * @param clientName The name of the client.
     * @param clientEmail The email address of the client.
     * @return The Bearer token as a String.
     */
    public static String getTokenAFterClientRegistration(String clientName, String clientEmail) {
        // Create the client registration request payload
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
        clientRegistrationRequest.setClientName(clientName);
        clientRegistrationRequest.setClientEmail(clientEmail);

        // Send POST request to register the client
        Response response = given()
                .contentType(ContentType.JSON)
                .body(clientRegistrationRequest)
                .when()
                .post(Endpoints.CLIENT_REGISTRATION_URL)
                .then()
                .statusCode(201)  // Assuming the status code 201 is returned upon successful client registration
                .extract()
                .response();

        // Extract and return the token from the response
        String token = response.jsonPath().getString("accessToken");
        if (token != null) {
            System.out.println("Client registration successful! Bearer Token: " + token);
            return token;
        } else {
            throw new RuntimeException("Failed to retrieve Bearer token.");
        }
    }
    /**
     * Creates an order with the given book ID and customer name and returns the orderId.
     *
     * @param token The Bearer token for authentication.
     * @param bookId The ID of the book to be ordered.
     * @param customerName The name of the customer placing the order.
     * @return The orderId of the created order.
     */
    public static String getOrderIDAfterCreatingOrder(String token, int bookId, String customerName) {
        // Create OrderRequest POJO and set the request data
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setBookId(bookId);  // Set book ID
        orderRequest.setCustomerName(customerName);  // Set customer name

        // Send POST request to /orders with the authentication token
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)  // Bearer token for authentication
                .body(orderRequest)
                .when()
                .post(Endpoints.ORDERS_URL)
                .then()
                .statusCode(201)  // HTTP Status Code 201 - Created
                .extract().response();

        // Extract orderId from the response and return it
        String orderId = response.jsonPath().getString("orderId");
        if (orderId != null) {
            System.out.println("Order created successfully! Order ID: " + orderId);
            return orderId;
        } else {
            throw new RuntimeException("Failed to create the order or retrieve orderId.");
        }
    }
}
