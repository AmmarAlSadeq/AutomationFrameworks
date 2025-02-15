Feature: Checkout Process

  Scenario: Complete the checkout process with valid credentials
    Given I visit the sauce demo
    When I login with valid username "standard_user" and password "secret_sauce"
    Then I should see the Products page
    When I add the two most expensive products to the cart
    And I click on the cart button
    Then I should see the Cart page with the selected products
    When I click on the Checkout button
    Then I should see the Checkout page
    When I fill the checkout form with "John" "Doe" "12345"
    And I click on the Continue button
    Then I should see the Overview page
    And I should verify the items total amount before taxes
    And I should verify the URL matches "https://www.saucedemo.com/checkout-step-two.html"
    When I click on the Finish button
    Then I should see the "Thank You" and "order has been dispatched" messages
