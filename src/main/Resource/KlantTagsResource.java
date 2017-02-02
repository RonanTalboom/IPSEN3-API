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
 *
 */
@Singleton
@Path("/klantTags")
@Produces(MediaType.APPLICATION_JSON)
public class KlantTagsResource {

    /**
     *
     */
    public KlantTagService service;

    /**
     * Default constructor
     * @param service
     */
    @Inject
    public void KlantTagsResource(KlantTagService service) {
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
        System.out.println("klantid: "+id);
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