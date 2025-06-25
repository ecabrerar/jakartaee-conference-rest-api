package com.eudriscabrera.examples.java.conference.services;

import com.eudriscabrera.examples.java.conference.entities.Session;
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
public class SessionService {

    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Session addSession(Session Session) {
        logger.info("Creating Session {0}", Session.getTitle());
        entityManager.persist(Session);

        return Session;
    }

    public List<Session> getAllSessions() {

        logger.info("Getting all Sessions");

        return entityManager.createQuery("SELECT b FROM Session b", Session.class).getResultList();
    }

    public Optional<Session> findById(String id) {
        logger.info("Getting Session by id {0}", id);
        return Optional.ofNullable(entityManager.find(Session.class, id));
    }

    public Session update(Session Session) {
        logger.info("Updating Session {0}", Session.getTitle());

        return entityManager.merge(Session);
    }

    public void clear() {
        Query removeAll = entityManager.createQuery("delete from Session");
        removeAll.executeUpdate();
    }
}
