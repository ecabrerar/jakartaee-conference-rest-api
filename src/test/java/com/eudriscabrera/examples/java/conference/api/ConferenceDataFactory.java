package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.entities.Speaker;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static jakarta.servlet.http.HttpServletResponse.SC_CREATED;

class ConferenceDataFactory extends BaseAPITest {

    private ConferenceDataFactory() {
    }

    static Speaker oneExistingSpeaker() {
         var newSpeaker = ConferenceFactory.createSpeaker();
         return given().
                 contentType(ContentType.JSON).
                 body(newSpeaker).
                 when().
                 post("/speakers").
                 then().
                 statusCode(SC_CREATED)
                 .extract()
                 .as(Speaker.class);
     }

     static Session oneExistingSession() {
         Speaker created = oneExistingSpeaker();

         var session = ConferenceFactory.createSession(created);

         return given().
                 contentType(ContentType.JSON).
                 body(session).
                 when().
                 post("/sessions").
                 then().
                 statusCode(SC_CREATED)
                 .extract()
                 .as(Session.class);


     }
}
