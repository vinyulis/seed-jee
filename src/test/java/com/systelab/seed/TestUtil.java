package com.systelab.seed;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class TestUtil {

    @Step("Expected result: The returning value is true")
    public static void checkResultIsTrue(boolean b) {
        Assertions.assertTrue(b);
    }

    @Step("Expected result: Field {0} is equals to {1}")
    public static void checkField(String field, String value, String returnedValue) {
        Assertions.assertEquals(value, returnedValue);
    }

    @Step("Expected result: {0} is not null")
    public static void checkObjectIsNotNull(String objectType, Object object) {
        Assertions.assertNotNull(object);
    }

    @Step("Expected result: {0} is null")
    public static void checkObjectIsNull(String objectType, Object object) {
        Assertions.assertNull(object);
    }

    @Step("Expected result: The error code is {0}")
    public static void checkThatIHaveAnException(int expectedCode, int code) {
        Assertions.assertEquals(expectedCode, code);
    }

    @Step("Expected result: {0} {1}")
    public static void checkANumber(String message, int expected, int value) {
        Assertions.assertEquals(expected, value);
    }

    @Step("Expected result: {0} {1}")
    public static void checkANumber(String message, long expected, long value) {
        Assertions.assertEquals(expected, value);
    }

    @Step("Expected result: The returned id is {1}")
    public static void printReturnedId(String idName, long id) {
        // Returning id
    }
}
