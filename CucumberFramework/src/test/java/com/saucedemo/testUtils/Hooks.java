package com.saucedemo.testUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    // This method runs before each Cucumber scenario
    @Before
    public void setUp() {
        WebBaseClass.initializeDriver();  // Initialize the WebDriver before the test starts
    }

    // This method runs after each Cucumber scenario
    @After
    public void tearDown() {
        WebBaseClass.stopDriver();  // Clean up and close the WebDriver after the test ends
    }
}