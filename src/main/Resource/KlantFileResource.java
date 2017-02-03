package main.Resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bestand;
import main.Services.KlantFileService;
import main.View;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;


/**
 *
 */
@Singleton
@Path("/klantBestand")
@Produces(MediaType.APPLICATION_JSON)
public class KlantFileResource {

    /**
     * Default constructor
     */
    public KlantFileResource() {

    }

    /**
     *
     */
    public KlantFileService klantFileService;

    /**
     * @param service
     */
    @Inject
    public void KlantFileResource(KlantFileService service) {
       this.klantFileService = service;
    }

    /**
     * @param id
     */

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Bestand> retrieveAll(@PathParam("id") int id) {
        return klantFileService.get(id);
    }


    /**
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("GUEST")
    public void delete(@PathParam("id") int id) {
        klantFileService.delete(id);
    }


    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(@PathParam("id") int id, Bestand bestand)
    {
        System.out.println("sangam"+bestand.getBase64());
      klantFileService.add(bestand);
    }


}