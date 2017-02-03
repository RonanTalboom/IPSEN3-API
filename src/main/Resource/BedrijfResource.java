package main.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Services.BedrijfService;
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
@Path("/bedrijven")
@Produces(MediaType.APPLICATION_JSON)
public class BedrijfResource {
    /**
     *
     */
    private final BedrijfService service;

    /**
     * Default constructor
     * @param service
     */
    @Inject
    public BedrijfResource(BedrijfService service) {
        this.service = service;
    }



    /**
     *
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Bedrijf> retrieveAll() {
        return service.getAll();
    }

    /**
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Bedrijf retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * @param bedrijf
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public Integer create(Bedrijf bedrijf) {
        return service.add(bedrijf);

    }

    /**
     * @param id
     * @param bedrijf
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id") int id, Bedrijf bedrijf) {
        bedrijf.setId(id);
        service.update(id,bedrijf);
    }

    /**
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

    /**
     * @param id
     */
    @DELETE
    @Path("/activeer/{id}")
    @RolesAllowed("ADMIN")
    public void activeer(@PathParam("id") int id) {
        service.activeer(id);
    }

}