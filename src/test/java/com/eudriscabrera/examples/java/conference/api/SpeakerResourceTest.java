package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Speaker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;

class SpeakerResourceTest {


    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "jakartaee-conference-rest-api/rest";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void createSpeakerSuccessfully() {

        var speaker = ConferenceFactory.createSpeaker();

        given().
                contentType(ContentType.JSON).
                body(speaker).
                when().
                post("/speakers").
                then().
                log().all().
                statusCode(SC_CREATED)
                .contentType(ContentType.JSON)
                .body("[0].id", equalTo(speaker.getId()),
                        "name",equalTo(speaker.getName()));

    }

    @Test
    void getSpeakers() {

        given()
                .when()
                .get("/speakers")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

    }

    @Test
    void speakerNotFound(){
        given().
                pathParam("id", UUID.randomUUID().toString()).
         when()
             .get("/speakers/{id}").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);

    }

    @Test
    void getExistingSpeaker() {

        Speaker created = ConferenceDataFactory.oneExistingSpeaker();

        given().
                pathParam("id", created.getId()).
                when()
                .get("/speakers/{id}").
                then().
                statusCode(HttpStatus.SC_OK).
                assertThat().contentType(ContentType.JSON).
                body(
                "id", equalTo(created.getId()),
                  "name",equalTo(created.getName()),
                "title", equalTo(created.getTitle()),
                "organization", equalTo(created.getOrganization()),
                "country", equalTo(created.getCountry())
        );

    }

    @Test
    void updateSpeaker() {
        Speaker created = ConferenceDataFactory.oneExistingSpeaker();

        created.setCountry("Dominican Republic");
        created.setOrganization("Dominicana JUG");
        created.setAccepted(true);

        given().
                contentType(ContentType.JSON).
                pathParam("id", created.getId()).
                body(created).
                when()
                .put("/speakers/{id}").
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "id", equalTo(created.getId()),
                        "name",equalTo(created.getName()),
                        "title", equalTo(created.getTitle()),
                        "organization", equalTo("Dominicana JUG"),
                        "country", equalTo("Dominican Republic"));


    }
}
