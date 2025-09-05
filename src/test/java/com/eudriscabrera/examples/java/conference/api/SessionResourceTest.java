package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Speaker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.*;

class SessionResourceTest extends BaseAPITest{


    @Test
    void sessionNotFound(){
        given().
                pathParam("id", UUID.randomUUID().toString()).
                when()
                .get("/sessions/{id}").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void getSessions() {
        given()
                .when()
                .get("/sessions")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);

    }

    @Test
    void findSession() {

    }

    @Test
    void createSession() {

        Speaker created = ConferenceDataFactory.oneExistingSpeaker();

        var session = ConferenceFactory.createSession(created);

        given().
                contentType(ContentType.JSON).
                body(session).
                when().
                post("/sessions").
                then().
                statusCode(SC_CREATED)
                .body("[0].id", equalTo(session.getId()),
                        "title", equalTo(session.getTitle()));


    }

    @Test
    void updateSession() {

        var session = ConferenceDataFactory.oneExistingSession();
        session.setDuration(Duration.of(60, ChronoUnit.MINUTES));
        session.setFeatured(true);

        given().
                contentType(ContentType.JSON).
                pathParam("id", session.getId()).
                body(session).
                when()
                .put("/sessions/{id}").
                then().
                statusCode(SC_OK)
                .body("id", equalTo(session.getId()),
                        "title", equalTo(session.getTitle()),
                        "featured", is(session.isFeatured()));
    }
}