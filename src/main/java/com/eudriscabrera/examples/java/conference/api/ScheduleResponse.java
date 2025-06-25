package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Session;
import jakarta.json.bind.annotation.JsonbProperty;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 * @author eudriscabrera
 *
 */
public record ScheduleResponse(
        @JsonbProperty("session") Session session,
        @JsonbProperty("room") String room,
        @JsonbProperty("sessionDate") LocalDate sessionDate,
        @JsonbProperty("duration") Duration duration,
        @JsonbProperty("startTime") LocalTime startTime) {
}