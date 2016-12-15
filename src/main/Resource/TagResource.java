package main.Resources;


import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Tag;
import main.Services.TagService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;


/**
 *
 */
@Singleton
@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagResource {

    /**
     *
     */
    public TagService service;


    /**
     * @param service
     */
    @Inject
    public TagResource(TagService service) {
        this.service = service;
    }

    /**
     *
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Tag> retrieveAll() {
        return service.getAll();
    }

//    /**
//     * @param id
//     */
//    public void retrieve(int id) {
//        // TODO implement here
//    }

    /**
     * @param tag
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public void create(Tag tag ) {
        service.add(tag);
    }

    /**
     * @param id
     * @param tag
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id")int id, Tag tag) {
        tag.setId(id);
        service.update(id,tag);
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