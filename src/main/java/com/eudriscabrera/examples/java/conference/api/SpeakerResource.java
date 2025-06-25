package com.eudriscabrera.examples.java.conference.api;

import com.eudriscabrera.examples.java.conference.entities.Speaker;
import com.eudriscabrera.examples.java.conference.services.SpeakerService;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.net.URI;

@Path("/speakers")
public class SpeakerResource {
    Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Inject
    SpeakerService speakerService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getSpeakers() {
        logger.warn("Getting all Speakers");

        return Response.ok(speakerService.getAllSpeakers()).build();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response findSpeaker(@PathParam("id") String id) {
        logger.info("Getting Speaker by id {0}", id);

        Speaker speaker = speakerService.findById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));

        return Response.ok(speaker).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Speaker speaker) {
        logger.info("Creating Speaker {0}", speaker.getName());

        try {
            Speaker created = speakerService.addSpeaker(speaker);
            return Response
                    .created(URI.create("/" + created.getId()))
                    .entity(created)
                    .build();
        } catch (PersistenceException ex) {
            logger.info("Error creating Speaker {0}", speaker.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") String id, Speaker updateSpeaker) {

        logger.info("Updating Speaker {0}", updateSpeaker.getName());
        try {

            Speaker speaker = speakerService.findById(id).orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
            speaker.setAccepted(updateSpeaker.isAccepted());
            speaker.setOrganization(updateSpeaker.getOrganization());
            speaker.setCountry(updateSpeaker.getCountry());
            speaker.setName(updateSpeaker.getName());
            speaker.setShortBio(updateSpeaker.getShortBio());

            return Response.ok(speakerService.update(speaker)).build();

        } catch (PersistenceException ex) {

            logger.error("Error updating Speaker {0}", updateSpeaker.getName());
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

}
