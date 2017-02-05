package main.Resource;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import main.Services.KlantTagService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Dit is de KlantTag Resource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor KlantTags.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
@Path("/klantTags")
@Produces(MediaType.APPLICATION_JSON)
public class KlantTagsResource {

    /**
     * Dit is een Object van KlantTagSevice. Dit is nodig om de request aftehandelen.
     */
    private final KlantTagService service;

    /**
     * constructor van KlantTagResource
     * @param service geinjecteerd in de klasse.
     */
    @Inject
    public KlantTagsResource(KlantTagService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van klantTags. aanroepbaar via een get request.
     * Stuurt een collectie van tag id's terug die gekoppeld zijn aan de klantid.
     * @param klantId van de desbetreffende klant.
     * @return integer collection van tag id's.
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Integer> retrieveAll(@PathParam("id") int klantId) {
        return service.get(klantId);
    }

    /**
     * Methode voor het toevoegen van klantTags. aanroepbaar via een post request.
     * verwacht een integer collection van tag id's en klantid.
     * stuur dit door naar de add methode van klantTagservice.
     * @param klantId van de desbetreffende klant.
     * @param tagIDs integer collection van tag id's.
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void create( @PathParam("id") int klantId, Collection<Integer> tagIDs) {
        service.add(tagIDs,klantId);
    }

    /**
     * Methode voor het verwijderen van klantTags. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van KlantTagservice.
     * @param klantId van de desbetreffende klant.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("GUEST")
    public void delete(@PathParam("id") int klantId) {
        service.delete(klantId);
    }

}