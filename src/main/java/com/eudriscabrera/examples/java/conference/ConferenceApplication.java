package com.eudriscabrera.examples.java.conference;

import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("rest")
public class ConferenceApplication extends Application {
    // Needed to enable Jakarta REST and specify path.
}
