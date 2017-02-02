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
 *
 */
@Singleton
@Path("/beheerders")
@Produces(MediaType.APPLICATION_JSON)
public class BeheerderResource {

    /**
     *
     */
    public BeheerderService service;


    /**
     * @param service
     */
    @Inject
    public BeheerderResource(BeheerderService service) {
        this.service = service;
    }

    /**
     *
     */

    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Beheerder> retrieveAll() {
        return service.getAll();

    }

    /**
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
     * @param beheerder
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public void create(Beheerder beheerder) {
        service.add(beheerder);
    }

    /**
     * @param id
     * @param authenticator
     * @param beheerder
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
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id, boolean isactief) {
        service.delete(id, isactief);
    }

    /**
     * @param authenticator
     */
    @GET
    @Path("/me")
    @RolesAllowed("BEHEERDER")
    public Beheerder authenticate(@Auth Beheerder authenticator) {
        return service.me(authenticator);
    }

}