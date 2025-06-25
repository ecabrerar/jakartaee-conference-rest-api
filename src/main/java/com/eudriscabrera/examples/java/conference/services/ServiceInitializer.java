package com.eudriscabrera.examples.java.conference.services;


import com.eudriscabrera.examples.java.conference.entities.Schedule;
import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.entities.Speaker;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author eudriscabrera
 *
 */
@ApplicationScoped
public class ServiceInitializer {

   private final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    SpeakerService speakerService;

    @Inject
    SessionService sessionService;

    @Inject
    ScheduleService scheduleService;

    public void initialize(@Observes @Initialized(ApplicationScoped.class) Object event){
        logger.info("Initializing sessions");

        if(speakerService.getAllSpeakers().isEmpty()){

            Speaker speaker1 = speakerService.addSpeaker(new Speaker("Freddy Peña", "Alphnology", "Software Engineer", "Dominican Republic", "Professor, Java Enthusiast, Vaadin Champion"));
            Speaker speaker2 =  speakerService.addSpeaker(new Speaker("Eudris Cabrera","Dominicana JUG","JUG Leader","Dominican Republic","Java Enthusiast, Linux, OSS"));
            Speaker speaker3 =speakerService.addSpeaker(new Speaker("Geovanny Mendoza","Barranquilla JUG","JUG Leader","Colombia","Java Developer,Vaadin Champion"));
            Speaker speaker4 =speakerService.addSpeaker(new Speaker("Brayan Muñoz","Dominicana JUG","JUG Leader","Dominican Republic","Java, Data Scientist, OSS"));

            Session session1 = sessionService.addSession(new Session("Rompiendo la UI (¡Antes de que lo haga el usuario!)", Duration.of(45, ChronoUnit.MINUTES), speaker1));
            Session session2 = sessionService.addSession(new Session("Pruebas de integración para servicios REST usando Rest Assured", Duration.of(45, ChronoUnit.MINUTES),speaker2));
            Session session3 = sessionService.addSession(new Session("Integración de Spring Boot con PayPal y Buenas Prácticas", Duration.of(45, ChronoUnit.MINUTES),speaker3));
            Session session4 = sessionService.addSession(new Session("Optimizing LLM Responses with Retrieval-Augmented Generation (RAG) in Java", Duration.of(45, ChronoUnit.MINUTES),speaker4));

            scheduleService.addSchedule(new Schedule(session1.getId(),"FCSI-401", LocalDate.of(2025,6,25),Duration.of(45, ChronoUnit.MINUTES), LocalTime.of(7,0,0)));
            scheduleService.addSchedule(new Schedule(session2.getId(),"FCSI-402", LocalDate.of(2025,6,25),Duration.of(45, ChronoUnit.MINUTES), LocalTime.of(7,0,0)));
            scheduleService.addSchedule(new Schedule(session3.getId(),"FCSI-403", LocalDate.of(2025,6,25),Duration.of(45, ChronoUnit.MINUTES), LocalTime.of(7,0,0)));
            scheduleService.addSchedule(new Schedule(session4.getId(),"FCSI-403", LocalDate.of(2025,6,25),Duration.of(45, ChronoUnit.MINUTES), LocalTime.of(7,0,0)));

        }
    }

}
