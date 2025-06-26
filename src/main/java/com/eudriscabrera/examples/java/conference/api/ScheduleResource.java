package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Schedule;
import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.services.ScheduleService;
import com.eudriscabrera.examples.java.conference.services.SessionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/schedules")
public class ScheduleResource {

    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    private ScheduleService scheduleService;

    @Inject
    private SessionService sessionService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(final Schedule schedule) {
        final Schedule created = scheduleService.addSchedule(schedule);
        return Response
                .created(URI.create("/" + created.getId()))
                .entity(created)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response retrieveById(@PathParam("id") final String id) {
        return scheduleService.findById(id)
                .map(schedule -> Response.ok(transform(schedule)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Produces("application/json")
    public Response allSchedules() {
        final List<Schedule> allSchedules = scheduleService.getAllSchedules();



        final GenericEntity<List<ScheduleResponse>> entity = buildEntity(allSchedules);
        return Response.ok(entity).build();
    }

    @GET
    @Path("/room/{roomCode}")
    @Produces("application/json")
    public Response allByRoom(@PathParam("roomCode") final String roomCode) {
        final List<Schedule> schedulesByRoom = scheduleService.findByRoom(roomCode);
        final GenericEntity<List<ScheduleResponse>> entity = buildEntity(schedulesByRoom);
        return Response.ok(entity).build();
    }

    @GET
    @Path("/active/{dateTime}")
    @Produces("application/json")
    public Response activeAtDate(@PathParam("dateTime") final String dateTimeString) {
        final LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
        final List<Schedule> schedulesByDate = scheduleService.findByDate(dateTime.toLocalDate());
        final List<Schedule> activeAtTime = schedulesByDate.stream()
                .filter(schedule -> isTimeInSchedule(dateTime.toLocalTime(), schedule))
                .collect(Collectors.toList());
        final GenericEntity<List<ScheduleResponse>> entity = buildEntity(activeAtTime);
        return Response.ok(entity).build();
    }

    @GET
    @Path("/all/{date}")
    @Produces("application/json")
    public Response allForDay(@PathParam("date") final String dateString) {
        final LocalDate date = LocalDate.parse(dateString);
        final List<Schedule> schedulesByDate = scheduleService.findByDate(date);
        final GenericEntity<List<ScheduleResponse>> entity = buildEntity(schedulesByDate);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{scheduleId}")
    public Response remove(@PathParam("scheduleId") final String scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return Response.noContent().build();
    }

    private GenericEntity<List<ScheduleResponse>> buildEntity(final List<Schedule> scheduleList) {

        List<ScheduleResponse>  listTransformed =  scheduleList.stream().map(this::transform).collect(Collectors.toList());

        return new GenericEntity<>(listTransformed) {
        };
    }

    private boolean isTimeInSchedule(final LocalTime currentTime, final Schedule schedule) {
        final LocalTime scheduleStartTime = schedule.getStartTime();
        final LocalTime scheduleEndTime = scheduleStartTime.plus(schedule.getDuration());
        return scheduleStartTime.isBefore(currentTime) &&
                scheduleEndTime.isAfter(currentTime);
    }

    private ScheduleResponse transform(Schedule schedule){
        Session session = sessionService.findById(schedule.getSessionId())
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));

        return new ScheduleResponse(session,schedule.getRoom(),schedule.getSessionDate(),schedule.getDuration(),schedule.getStartTime());

    }


}
