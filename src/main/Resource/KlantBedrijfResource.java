package main.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
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

    @GET
    @Path("/overige/{id}")
    @RolesAllowed("GUEST")
    public Collection<Klant> retrieveOverigeAll(@PathParam("id") int id) {
        return service.getOverigeAll(id);
    }


    @GET
    @Path("/gekoppeldebedrijven/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Bedrijf> retrieveAllBedrijven(@PathParam("id") int id) {
        return service.getAllBedrijven(id);
    }

    @GET
    @Path("/overigebedrijven/{id}")
    @RolesAllowed("GUEST")
    public Collection<Bedrijf> retrieveOverigeAllBedrijven(@PathParam("id") int id) {
        return service.getOverigeBedrijvenAll(id);
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
    @PUT
    @Path("/{bedrijfid}/{klantid}")
    @RolesAllowed("GUEST")
    public void insert(@PathParam("bedrijfid") int bedrijfID,@PathParam("klantid") int klantID) {
        service.add(bedrijfID,klantID);
    }
//
//    /**
//     * @param id
//     */
    @DELETE
    @Path("/{bedrijfid}/{klantid}")
    public void delete(@PathParam("bedrijfid") int bedrijfid,@PathParam("klantid") int klantid) {

        service.delete(bedrijfid);
    }

}