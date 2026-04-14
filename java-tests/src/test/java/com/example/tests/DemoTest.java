package com.example.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoTest {

    @Test
    public void testAddition() {
        TestLogger.log("Running Java JUnit test: testAddition");
        assertEquals(4, 2 + 2, "2 + 2 should equal 4");
    }

    @Test
    public void testCondition() {
        TestLogger.log("Running Java JUnit test: testCondition");
        assertTrue(10 > 5, "10 should be greater than 5");
    }
}
