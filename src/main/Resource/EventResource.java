package main.Resource;


import io.dropwizard.auth.Auth;
import main.Model.Beheerder;
import main.Model.Event;
import main.Services.EventService;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import main.View;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Dit is de EventResource. Deze klasse is verantwoordelijk voor het afhandelen van http request voor Bedrijf.
 *
 * @author Ruben van Til
 * @version 1.0, Februari 2017
 */
@Singleton
@Path("/agenda")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {


    /**
     * Dit is een Object van EventService, deze is nodig om de request aftehandelen.
     */
    public final EventService service;


    /**
     * Constructor van EventResource
     *
     * @param service Geinjecteerd in de klasse
     */
    @Inject
    public EventResource(EventService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van Events. aanroepbaar via een get request.
     * Stuurt een collectie van Events die behooren tot de Beheerder die dit request maakt.
     *
     * @param authenticator Beheerder die het request uitvoert.
     * @return Collectie van events
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Event> retrieveAll(@Auth Beheerder authenticator) {
        return service.getAll(authenticator);
    }

    /**
     * Methode voor het ophalen van een enkel Event. Aanroepbaar met een GET request.
     * Stuurt het evenement toe met het meegegeven id, indien dat event behoort tot de beheerder die het request maakt.
     *
     * @param id            ID van het event dat opgehaald moet worden.
     * @param authenticator Beheerder die het request uitvoerd.
     * @return Opgevraagd event.
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Event retrieve(@Auth Beheerder authenticator, @PathParam("id") int id) {
        return service.get(authenticator, id);
    }

    /**
     * Methode voor het maken van een nieuw Event. Aanroepbaar met een POST request.
     * het in JSON meegegeven event wordt toegevoegd aan de database.
     *
     * @param event Het evenement dat moet worden toegevoegd aan de database.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void create(Event event) {
        service.add(event);
    }

    /**
     * Methode voor het bewerken van een evenement. Kan worden aangeroepen met een PUT request.
     * Het meegegeven JSON object overschrijft het bestaande object met hetzelfde ID.
     *
     * @param event Het evenement datmoet worden bewerkt.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(Event event) {
        service.update(event);
    }

    /**
     * Methode voor het verwijderen van een evenement. Kan worden aangeroepen met een DELETE request.
     * Het event met het meegegeven ID wordt verwijdert uit de database.
     *
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }


}
