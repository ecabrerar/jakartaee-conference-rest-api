package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.entities.Speaker;
import net.datafaker.Faker;

class ConferenceFactory {

    public static Speaker createSpeaker() {
        Faker faker = new Faker();
        return new Speaker(faker.name().fullName(),
                faker.company().name(), faker.job().title(), faker.country().name(), faker.programmingLanguage().name());
    }

    public static Session createSession(){
        return null;
    }
}
