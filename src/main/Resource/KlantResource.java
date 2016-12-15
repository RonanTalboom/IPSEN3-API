package main.Resources;




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
     *
     */
    public final KlantService service;


    /**
     * @param service
     */
    @Inject
    public KlantResource(KlantService service) {
        this.service = service;
    }

    /**
     * 
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Klant> retrieveAll() {
        return service.getAll();
    }

    /**
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Klant retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * @param klant
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Klant klant) {
        service.add(klant);
    }

    /**
     * @param id 
     * @param authenticator 
     * @param klant
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id") int id, @Auth Beheerder authenticator, Klant klant) {

        service.add(klant);
    }

    /**
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN") // ???
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

    /**
     * @param authenticator
     */
    @DELETE
    @Path("/me")
    @RolesAllowed("ADMIN") // ???
    public void authenticate(Klant authenticator) {
        // TODO implement here
    }

}