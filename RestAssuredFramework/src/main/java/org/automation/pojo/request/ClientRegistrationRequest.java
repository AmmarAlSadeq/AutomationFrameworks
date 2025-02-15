package org.automation.pojo.request;

import lombok.Getter;
import lombok.Setter;

/**
 * ClientRegistrationRequest class represents the request structure for registering a client.
 * It contains the client's name and email required for registration.
 */
@Getter  // Lombok annotation to generate getter methods
@Setter  // Lombok annotation to generate setter methods
public class ClientRegistrationRequest {
    private String clientName;   // Name of the client
    private String clientEmail; // Email of the client
}
