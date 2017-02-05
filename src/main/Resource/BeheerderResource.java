package main.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.dropwizard.auth.Auth;
import main.Model.Beheerder;
import main.Services.BeheerderService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Dit is de Tag Resource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor tags.
 * @author Murtaza Aydogdu
 * @version 1.0, Februari 2017
 */
@Singleton
@Path("/beheerders")
@Produces(MediaType.APPLICATION_JSON)
public class BeheerderResource {

    /**
     * Dit is een Object van BeheerderService. Dit is nodig om de request aftehandelen.
     */
    public BeheerderService service;


    /**
     * constructor van TagResource
     * @param service geinjecteerd in de class.
     */
    @Inject
    public BeheerderResource(BeheerderService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van een Beheerder. aanroepbaar via een get request.
     * Stuurt een collectie van Beheerder object terug.
     * @return collection van Beheerder.
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Beheerder> retrieveAll() {
        return service.getAll();

    }

    /**
     *
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Beheerder retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * Methode voor het toevoegen van een Beheerder. Deze is aanroepbaar via een post request.
     * verwacht een object van Beheerder en stuurt dit door naar de add methode van BeheerderService.
     * @param beheerder object met alle nodige waardes.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public void create(Beheerder beheerder) {
        service.add(beheerder);
    }

    /**
     * Methode voor het wijzigen van een Beheerder. Deze is aanroepbaar via een put request.
     * verwacht een object van Beheerder en een id en stuurt dit door naar de update methode van BeheerderService.
     * @param id van de desbetreffende tag
     * @param authenticator beheerder die ingelogd is.
     * @param beheerder object met alle nodige waardes.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@PathParam("id") int id, @Auth Beheerder authenticator, Beheerder beheerder) {

        beheerder.setId(id);
        service.update(authenticator,id,beheerder);
    }

    /**
     * Methode voor het verwijderen van een Beheerder. Deze is aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van BeheerderService.
     * @param id van de desbetreffende Beheerder.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id, boolean isactief) {
        service.delete(id, isactief);
    }

    /**
     * Methode voor het inloggen van een beheerder/admin. Deze is aanroepbaar via een get request.
     * Verwacht een object van Beheerder en stuurt dit door naar de me methode van BeheerderService.
     * @param authenticator
     */
    @GET
    @Path("/me")
    @RolesAllowed("BEHEERDER")
    public Beheerder authenticate(@Auth Beheerder authenticator) {
        return service.me(authenticator);
    }

}