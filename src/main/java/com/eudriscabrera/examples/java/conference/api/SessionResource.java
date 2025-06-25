package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Session;
import com.eudriscabrera.examples.java.conference.services.SessionService;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

@Path("/sessions")
public class SessionResource {

    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    SessionService sessionService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getSessions() {
        logger.warn("Getting all Sessions");

        return Response.ok(sessionService.getAllSessions()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response findSession(@PathParam("id") String id) {
        logger.info("Getting Session by id {0}", id);

        Session Session = sessionService.findById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NO_CONTENT));

        return Response.ok(Session).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Session Session) {
        logger.info("Creating Session {0}", Session.getTitle());
        try {
            return Response.ok(sessionService.addSession(Session)).build();
        } catch (PersistenceException ex) {
            logger.info("Error creating Session {0}", Session.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") Integer id, Session Session) {

        logger.info("Updating Session {0}", Session.getTitle());
        try {
            return Response.ok(sessionService.update(Session)).build();

        } catch (PersistenceException ex) {

            logger.error("Error updating Session {0}", Session.getTitle());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

}
