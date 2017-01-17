package main.Resources;

/**
 * Created by Ruben van Til on 17-1-2017.
 */

        import io.dropwizard.auth.Auth;
        import main.Model.Beheerder;
        import main.Model.Event;
        import main.Services.EventService;
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
@Path("/agenda")
@Produces(MediaType.APPLICATION_JSON)
public class EventResource {





    /**
     *
     */
    public final EventService service;


    /**
     * @param service
     */
    @Inject
    public EventResource(EventService service) {
        this.service = service;
    }

    /**
     *
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Collection<Event> retrieveAll() {
        return service.getAll();
    }

    /**
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("GUEST")
    public Event retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * @param event
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    public void create(Event event) {
        service.add(event);
    }

    /**
     * @param id
     * @param authenticator
     * @param event
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("GUEST")
    public void update(@PathParam("id") int id, @Auth Beheerder authenticator, Event event) {

        service.add(event);
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
    @DELETE
    @Path("/me")
    @RolesAllowed("ADMIN") // ???
    public void authenticate(Event authenticator) {
        // TODO implement here
    }

}
