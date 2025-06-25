package com.eudriscabrera.examples.java.conference.services;

import com.eudriscabrera.examples.java.conference.entities.Schedule;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author eudriscabrera
 *
 */
@Stateless
public class ScheduleService {
    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @PersistenceContext
    private EntityManager entityManager;

    public Schedule addSchedule(Schedule schedule) {
        logger.info("Creating Schedule {0}", schedule.getId());


        entityManager.persist(schedule);

        return schedule;
    }

    public List<Schedule> getAllSchedules() {

        logger.info("Getting all Schedules");

        return entityManager.createQuery("SELECT b FROM Schedule b", Schedule.class).getResultList();
    }

    public Optional<Schedule> findById(String id) {
        logger.info("Getting Schedule by id {0}", id);
        return Optional.ofNullable(entityManager.find(Schedule.class, id));
    }

    public Schedule update(Schedule schedule) {
        logger.info("Updating Schedule {0}", schedule.getId());

        if (schedule.getId() == null) {
            return addSchedule(schedule);
        }

        return entityManager.merge(schedule);
    }

    public void deleteSchedule(final String scheduleId) {
        if (scheduleId != null) {
            Optional.ofNullable(entityManager.find(Schedule.class, scheduleId))
                    .ifPresent((schedule) -> entityManager.remove(schedule));
        }
    }

    public List<Schedule> findByRoom(final String room) {

        return entityManager
                .createQuery("SELECT s FROM Schedule s where s.room LIKE :room", Schedule.class)
                .setParameter("room", room)
                .getResultList();
    }

    public List<Schedule> findByDate(final LocalDate date) {

        return entityManager
                .createQuery("SELECT s FROM Schedule s where s.sessionDate=:sessionDate", Schedule.class)
                .setParameter("sessionDate", date)
                .getResultList();
    }

    public void clear() {
        Query removeAll = entityManager.createQuery("delete from Schedule");
        removeAll.executeUpdate();
    }
}
