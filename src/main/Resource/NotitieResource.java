package main.Resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Model.Notitie;
import main.Services.NotitieService;
import main.View;
import org.junit.runners.Parameterized;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 */
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/notities")
public class NotitieResource {


    /**
     *
     */
    public NotitieService service;

    @Inject
    public void NotitieResource(NotitieService service) {
        this.service = service;
    }

    /**
     *
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> retrieveAll() {
        return service.getAll();
    }

    /**
     * 1 notitie ophalen
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Notitie retrieve(int id) {
        return service.get(id);
    }

    /**
     * een nieuwe notitie aanmaken
     * @param notitie
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void create(Notitie notitie) {
        service.add(notitie);
    }

    /**
     * één notitie bewerken
     * @param id
     * @param notitie
     */
    @PUT
    @Path("/{id}")
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@PathParam("id") int id, Notitie notitie) {
        service.update(id,notitie);
    }

    /**
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }


    @GET
    @Path("/klant/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> klantNotitie(@PathParam("id") int id) {

        return service.getKlantNotitie(id);
    }

    @GET
    @Path("/bedrijf/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> klantBedrijf(@PathParam("id")int id) {
        return service.getBedrijfNotitie(id);
    }

}