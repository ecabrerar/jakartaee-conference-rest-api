package com.eudriscabrera.examples.java.conference.api;


import com.eudriscabrera.examples.java.conference.entities.Schedule;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        var sessionCreated =  ConferenceDataFactory.oneExistingSession();

        var newSchedule  = ConferenceFactory.addSchedule(sessionCreated);

       var scheduleReturned = given().
                contentType(ContentType.JSON).
                body(newSchedule).
                when().
                post("/schedules").
                then().
                statusCode(SC_CREATED).
                extract().
                as(Schedule.class);

        assertEquals(newSchedule.getRoom(), scheduleReturned.getRoom());
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