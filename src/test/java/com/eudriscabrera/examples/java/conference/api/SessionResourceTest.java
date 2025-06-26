package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Speaker;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

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

        var newSpeaker = ConferenceFactory.createSpeaker();

        Speaker created = given().
                contentType(ContentType.JSON).
                body(newSpeaker).
                when().
                post("/speakers").
                then().
                statusCode(SC_CREATED)
                .extract()
                .as(Speaker.class);

        var session = ConferenceFactory.createSession(created);

        given().
                contentType(ContentType.JSON).
                body(session).
                when().
                post("/sessions").
                then().
                statusCode(SC_OK)
                .body("[0].id", equalTo(session.getId()));


    }

    @Test
    void update() {
    }
}