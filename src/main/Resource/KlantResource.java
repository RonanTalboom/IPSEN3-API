package main.Resource;




import io.dropwizard.auth.Auth;
import main.Model.Beheerder;
import main.Model.Klant;
import main.Services.KlantService;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import main.View;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * 
 */
@Singleton
@Path("/klanten")
@Produces(MediaType.APPLICATION_JSON)
public class KlantResource {

    /**
     * Dit is een Object van KlantService. Dit is nodig om de request aftehandelen.
     */
    public final KlantService service;


    /**
     * constructor van KlantResource
     * @param service geinjecteerd in de klasse.
     */
    @Inject
    public KlantResource(KlantService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van een klanten. aanroepbaar via een get request.
     * Stuurt een collectie van klanten objecten terug.
     * @return collection van klanten.
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Klant> retrieveAll() {
        return service.getAll();
    }

    /**
     * Methode voor het ophalen van een klant. aanroepbaar via een get request.
     * Stuurt een collectie van klant objecten terug.
     * @return een klant.
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Klant retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * Methode voor het toevoegen van een klant. aanroepbaar via een post request.
     * verwacht een object van klant en stuur dit door naar de add methode van klantService.
     * @param klant object met alle nodige waardes.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Integer create(Klant klant) {
        return service.add(klant);
    }

    /**
     * Methode voor het wijzigen van een Klant. aanroepbaar via een put request.
     * verwacht een object van Klant  stuur dit door naar de update methode van klantService.
     * @param klant object met alle nodige waardes.
     */
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@Auth Beheerder authenticator, Klant klant) {
        service.update(klant);
    }
    /**
     * Methode voor het deactiveren van een Klant. aanroepbaar via een delete request.
     *  stuur dit door naar de delete methode van klantService.
     * @param id van de desbetreffende tag.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

    /**
     * Methode voor het activweren van een Klant. aanroepbaar via een delete request.
     *  stuur dit door naar de delete methode van klantService.
     * @param id van de desbetreffende tag.
     */
    @DELETE
    @Path("/activeer/{id}")
    @RolesAllowed("BEHEERDER")
    public void activeer(@PathParam("id") int id) {
        service.activeer(id);
    }

}