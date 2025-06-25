package com.eudriscabrera.examples.java.conference.entities;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * @author eudriscabrera
 *
 */
@Entity
@Table(name = "schedules")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String sessionId;
    private String room;
    private LocalDate sessionDate;
    private Duration duration;
    private LocalTime startTime;

    public Schedule(
            @JsonbProperty("sessionId") String sessionId,
            @JsonbProperty("room") String room,
            @JsonbProperty("sessionDate") LocalDate sessionDate,
            @JsonbProperty("duration") Duration duration,
            @JsonbProperty("startTime") LocalTime startTime) {
        this.sessionId = sessionId;
        this.room = room;
        this.sessionDate = sessionDate;
        this.duration = duration;
        this.startTime = startTime;
    }

    public Schedule() {
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(sessionId, schedule.sessionId) && Objects.equals(room, schedule.room) && Objects.equals(sessionDate, schedule.sessionDate) && Objects.equals(duration, schedule.duration) && Objects.equals(startTime, schedule.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionId, room, sessionDate, duration, startTime);
    }
}
