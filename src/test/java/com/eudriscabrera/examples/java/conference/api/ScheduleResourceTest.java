package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Session;
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

class ScheduleResourceTest extends BaseAPITest{



    @Test
    void scheduleNotFound(){
        given().
                pathParam("id", UUID.randomUUID().toString()).
                when()
                .get("/schedule/{id}").
                then().
                statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    void addSchedule() {

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

        var sessionCreated =  given().
                contentType(ContentType.JSON).
                body(session).
                when().
                post("/sessions").
                then().
                statusCode(SC_CREATED)
                .extract()
                .as(Session.class);


       var newSchedule  = ConferenceFactory.addSchedule(sessionCreated);

        given().
                contentType(ContentType.JSON).
                body(newSchedule).
                when().
                post("/schedules").
                then().
                statusCode(SC_CREATED);
    }

    @Test
    void retrieveById() {

    }

    @Test
    void allSchedules() {
        given()
                .when()
                .get("/schedules")
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    void allByRoom() {
    }

    @Test
    void activeAtDate() {
    }

    @Test
    void allForDay() {
    }

    @Test
    void remove() {
    }
}