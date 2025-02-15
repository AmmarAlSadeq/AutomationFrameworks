package org.automation.apis;

/**
 * Endpoints class contains all the API endpoints used in the test framework.
 * These endpoints are defined as constants for easy access and maintenance.
 */
public class Endpoints {
    // Base URL for the API
    public static final String BASE_URL = "https://simple-books-api.glitch.me";

    // Endpoint for client registration
    public static final String CLIENT_REGISTRATION_URL = BASE_URL + "/api-clients";

    // Endpoint for fetching books
    public static final String BOOKS_URL = BASE_URL + "/books";

    // Endpoint for managing orders
    public static final String ORDERS_URL = BASE_URL + "/orders";
}
