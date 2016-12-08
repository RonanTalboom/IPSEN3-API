package Resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Notitie;
import main.Services.NotitieService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 *
 */
@Singleton
@Path("/Notities")
public class NotitieResource {


    /**
     *
     */
    public final NotitieService service;

    @Inject
    public void NotitieResource(NotitieService service) {
        this.service = service;
    }

    /**
     *
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Notitie> retrieveAll() {
        return service.getAll();
    }

    /**
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Notitie retrieve(int id) {
        return service.get(id);
    }

    /**
     * @param notitie
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Notitie notitie) {
        service.add(notitie);
    }

    /**
     * @param id
     * @param notitie
     */
    public void update(int id, Notitie notitie) {
        service.update(id,notitie);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        service.delete(id);
    }

}