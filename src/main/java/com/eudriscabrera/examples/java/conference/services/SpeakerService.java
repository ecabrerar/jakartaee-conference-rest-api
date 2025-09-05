package com.eudriscabrera.examples.java.conference.services;

import com.eudriscabrera.examples.java.conference.entities.Speaker;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;


/**
 * @author eudriscabrera
 *
 */
@Stateless
public class SpeakerService {
    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Speaker addSpeaker(Speaker speaker) {
        logger.info("Creating speaker {0}", speaker.getName());
        entityManager.persist(speaker);

        return speaker;
    }

    public List<Speaker> getAllSpeakers() {

        logger.info("Getting all Speakers");

        return entityManager.createQuery("SELECT b FROM Speaker b", Speaker.class).getResultList();
    }

    public Optional<Speaker> findById(String id) {
        logger.info("Getting Speaker by id {0}", id);
        return Optional.ofNullable(entityManager.find(Speaker.class, id));
    }

    public Speaker update(Speaker speaker) {
        logger.info("Updating Speaker {0}", speaker.getName());

        return entityManager.merge(speaker);
    }

    public void clear() {
        Query removeAll = entityManager.createQuery("delete from Speaker");
        removeAll.executeUpdate();
    }
}
