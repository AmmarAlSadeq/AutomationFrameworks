package org.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.automation.apis.Endpoints;
import org.automation.pojo.request.ClientRegistrationRequest;
import org.automation.pojo.request.OrderRequest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

/**
 * Helper class containing utility methods for common tasks such as reading JSON data,
 * generating random emails, and more.
 */
public class Helper {

    /**
     * Reads JSON data from a file and converts it into a list of HashMaps.
     *
     * @param jsonPath The path to the JSON file.
     * @return A list of HashMaps containing the JSON data.
     * @throws IOException If the file cannot be read.
     */
    public static List<HashMap<String, String>> getJsonData(String jsonPath) throws IOException {
        // Read the JSON file content as a string
        String jsonContent = FileUtils.readFileToString(new File(jsonPath));

        // Use Jackson ObjectMapper to parse the JSON string into a list of HashMaps
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    /**
     * Generates a random email address.
     *
     * @return A random email address.
     */
    public static String getRandomEmail() {
        // Define the characters that can be used in the email prefix
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder emailPrefix = new StringBuilder();
        Random random = new Random();

        // Generate a random string of 15 characters for the email prefix
        for (int i = 0; i < 15; i++) {
            emailPrefix.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Append a domain to create a valid email address
        String randomEmail = emailPrefix.toString() + "@example.com";
        return randomEmail;
    }
}
