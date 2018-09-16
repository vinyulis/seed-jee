package com.systelab.seed.rest;

import io.qameta.allure.Feature;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Test Class to check Seed REST Api behaviour following a BDD approach (given-when-then)
 *
 * Note: See FunctionalTest parent class to understand how to obtain the rest entry endpoint
 * Note: Consider to change the method naming to pass Codacy checks
 */

@Feature("Rest Assured Test Suite")
@DisplayName("Rest Assured Test Suite")
public class SeedRestTest extends FunctionalTest {

    @Test
    @DisplayName("Basic ping test to check that seed REST API is up")
    public void basicPingTest() {
        given().when().get("/patients").then().statusCode(200);
    }

    @Test
    @DisplayName("Test incorrect input data checking the unexpected behaviour")
    public void invalidPatientIdTest() {
        given().when().get("/patients/patient/155").then().statusCode(404);
    }

    @Test
    @DisplayName("Test to check the expected body response content")
    public void givenPatientRequestCheckBodyResponse() {
        // given-when calling doGetResponse method
        Response response = doGetResponse("/patients");
        response.then().body(containsString("Worldwide"));
    }

    @Test
    @DisplayName("Test to check Json content in an attribute")
    public void givenUrlWhenSuccessOnGetsResponseAndJsonHasRequiredKVThenCorrect() {
        given().when().get("patients").then().statusCode(200).assertThat()
                .body("content.address.zip", hasItems("08110"));
    }

    @DisplayName("Test Json Schema Response")
    @Disabled()
    @Test
    public void givenUrlWhenValidatesResponseWithStaticSettingsThenCorrect() {
        Response response = doGetResponse("/patients");
        response.then().assertThat().body(matchesJsonSchemaInClasspath
                ("json-schema.json").using(JsonSchemaValidator.settings.with().checkedValidation(false)));
    }

}
