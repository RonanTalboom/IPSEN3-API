package main.Resource;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Services.BedrijfTagService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Dit is de BedrijfTag Resource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor BedrijfTags.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
@Path("/bedrijfTags")
@Produces(MediaType.APPLICATION_JSON)
public class BedrijfTagResource {

    /**
     * Dit is een Object van BedrijfTagService. Dit is nodig om de request aftehandelen.
     */
    private final BedrijfTagService service;

    /**
     * constructor van BedrijfTagResource.
     * @param service geinjecteerd in de klasse.
     */
    @Inject
    public BedrijfTagResource(BedrijfTagService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van bedrijfTags. aanroepbaar via een get request.
     * Stuurt een collectie van tag id's terug die gekoppeld zijn aan de bedrijfId.
     * @param bedrijfId van de desbetreffende bedrijf.
     * @return integer collection van tag id's.
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Integer> retrieveAll(@PathParam("id") int bedrijfId) {
        return service.get(bedrijfId);
    }

    /**
     * /**
     * Methode voor het toevoegen van bedrijfTags. aanroepbaar via een post request.
     * verwacht een integer collection van tag id's en bedrijfId.
     * stuur dit door naar de add methode van BedrijfTagservice.
     * @param bedrijfId van de desbetreffende bedrijf.
     * @param tagIDs integer collection van tag id's.
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void create(@PathParam("id") int bedrijfId, Collection<Integer> tagIDs) {
        service.add(tagIDs,bedrijfId);
    }

    /**
     * Methode voor het verwijderen van bedrijfTags. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van BedrijfTagservice.
     * @param bedrijfId van de desbetreffende klant.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("GUEST")
    public void delete(@PathParam("id") int bedrijfId) {
        service.delete(bedrijfId);
    }

}