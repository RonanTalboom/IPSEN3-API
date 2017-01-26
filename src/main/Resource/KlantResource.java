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

//    /**
//     *
//     */
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    @RolesAllowed("GUEST")
//    public Integer Klantid(Klant klant){
//        service.getklant(klant);
//        System.out.println(1);
//        return 1;
//    }

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
    @RolesAllowed("GUEST")
    public Integer create(Klant klant) {
        service.add(klant);
        return service.getklant(klant);
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
        service.update(id,klant);
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
//    @DELETE
//    @Path("/me")
//    @RolesAllowed("ADMIN") // ???
//    public Klant authenticate(@Auth Klant authenticator) {
//        return service.me(authenticator);
//    }

}