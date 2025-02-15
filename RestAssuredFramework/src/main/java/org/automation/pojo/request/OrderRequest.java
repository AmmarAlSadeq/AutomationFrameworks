package org.automation.pojo.request;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderRequest class represents the request structure for creating an order.
 * It contains the book ID and customer name required to place an order.
 */
@Getter  // Lombok annotation to generate getter methods
@Setter  // Lombok annotation to generate setter methods
public class OrderRequest {
    private int bookId;         // ID of the book to be ordered
    private String customerName;  // Name of the customer placing the order
}
