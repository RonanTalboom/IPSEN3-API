package main.Resource;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import main.Model.Beheerder;
import main.Model.Tag;
import main.Services.TagService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


/**
 * Dit is de Tag Resource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor tags.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    /**
     * Dit is een Object van TagSevice. Dit is nodig om de request aftehandelen.
     */
    private final TagService service;


    /**
     * constructor van TagResource
     * @param service geinjecteerd in de klasse.
     */
    @Inject
    public TagResource(TagService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van een Tags. aanroepbaar via een get request.
     * Stuurt een collectie van tag objecten terug.
     * @return collection van tags.
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Tag> retrieveAll() {
        return service.getAll();
    }


    /**
     * Methode voor het toevoegen van een Tag. aanroepbaar via een post request.
     * verwacht een object van Tag en stuur dit door naar de add methode van Tagservice.
     * @param tag object met alle nodige waardes.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public void create(Tag tag ) {
        service.add(tag);
    }

    /**
     * Methode voor het wijzigen van een Tag. aanroepbaar via een put request.
     * verwacht een object van Tag en een id en stuur dit door naar de update methode van Tagservice.
     * @param id van de desbetreffende tag.
     * @param tag object met alle nodige waardes.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@PathParam("id")int id, @Auth Beheerder authenticator, Tag tag) {
        System.out.println(authenticator.getName());
        service.update(tag);
    }

    /**
     * Methode voor het verwijderen van een Tag. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van Tagservice.
     * @param id van de desbetreffende tag.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

}