package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Schedule;
import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.entities.Speaker;
import net.datafaker.Faker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

class ConferenceFactory {
    private static final Faker faker = new Faker();

    public static Speaker createSpeaker() {
        return new Speaker(faker.name().fullName(),
                faker.company().name(), faker.job().title(), faker.country().name(), faker.programmingLanguage().name());
    }

    public static Session createSession(Speaker speaker){

       return new Session(faker.lorem().characters(), Duration.of(45, ChronoUnit.MINUTES), speaker);

    }

    public static Schedule addSchedule(Session session){
       return new Schedule(session.getId(),faker.house().room(), LocalDate.of(2025,6,25),Duration.of(45, ChronoUnit.MINUTES), LocalTime.of(7,0,0));
    }
}
