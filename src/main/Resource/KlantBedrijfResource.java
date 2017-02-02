package main.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Klant;
import main.Services.KlantBedrijfService;
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
@Path("/bedrijven/klanten")
@Produces(MediaType.APPLICATION_JSON)
public class KlantBedrijfResource {
    /**
     *
     */
    private final KlantBedrijfService service;

    /**
     * Default constructor
     * @param service
     */
    @Inject
    public KlantBedrijfResource(KlantBedrijfService service) {
        this.service = service;
    }



    /**
     *
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Klant> retrieveAll(@PathParam("id") int id) {
        return service.getAll(id);
    }

//    /**
//     * @param id
//     */
//    @GET
//    @Path("/{id}")
//    @JsonView(View.Public.class)
//    @RolesAllowed("GUEST")
//    public Bedrijf retrieve(@PathParam("id") int id) {
//        return service.get(id);
//    }
//
//    /**
//     * @param bedrijf
//     */
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    public void create(Bedrijf bedrijf) {
//        service.add(bedrijf);
//    }
//
//    /**
//     * @param id
//     * @param bedrijf
//     */
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @JsonView(View.Protected.class)
//    @RolesAllowed("GUEST")
//    public void update(@PathParam("id") int id, Bedrijf bedrijf) {
//        bedrijf.setId(id);
//        service.update(id,bedrijf);
//    }
//
//    /**
//     * @param id
//     */
    @DELETE
    @Path("/{bedrijfid}/{klantid}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("bedrijfid") int bedrijfid,@PathParam("klantid") int klantid) {

        service.delete(bedrijfid);
    }

}