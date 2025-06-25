package com.eudriscabrera.examples.java.conference.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseAPITest {

   @BeforeAll
   public static void beforeAllTests(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "jakartaee-conference-rest-api/rest";

        //Enable log if validation fails
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
