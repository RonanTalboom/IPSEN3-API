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
 *
 */
@Singleton
@Path("/bedrijfTags")
@Produces(MediaType.APPLICATION_JSON)
public class BedrijfTagResource {

    /**
     *
     */
    public BedrijfTagService service;

    /**
     * Default constructor
     * @param service
     */
    @Inject
    public void BedrijfTagResource(BedrijfTagService service) {
        this.service = service;
    }

    /**
     *
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Integer> retrieveAll(@PathParam("id") int id) {
        System.out.println("bedrijfID: "+id);
        return service.get(id);
    }

    /**
     * @param id
     */
    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void create( Collection<Integer> tagIDs, @PathParam("id") int id) {
        System.out.println("bedrijfID: "+id);
        service.add(tagIDs,id);
    }

    /**
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("GUEST")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }


}