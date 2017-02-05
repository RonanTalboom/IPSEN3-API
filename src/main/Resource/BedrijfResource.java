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
 *Dit is de BedrijfResource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor Bedrijf.
 *@author Mohamed El Baze
 *@version 1.0, Januari 2017
 */
@Singleton
@Path("/bedrijven")
@Produces(MediaType.APPLICATION_JSON)
public class BedrijfResource {

    /**
     * Dit is een Object van BedrijfService. Dit is nodig om de request aftehandelen.
     */
    private final BedrijfService service;

    /**
     * Constructor van BedrijfResource
     * @param service
     */
    @Inject
    public BedrijfResource(BedrijfService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van bedrijven. aanroepbaar via een get request.
     * Stuurt een collectie van Bedrijf gegevens terug.
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Bedrijf> retrieveAll() {
        return service.getAll();
    }

    /**
     * Methode voor het ophalen van bedrijven. aanroepbaar via een get request.
     * Stuurt een Object van Bedrijf gegevens terug die gekoppeld zijn aan de bedrijfId.
     * @param id
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Bedrijf retrieve(@PathParam("id") int id) {
        return service.get(id);
    }

    /**
     * Methode voor het toevoegen van een Bedrijf. aanroepbaar via een post request.
     * verwacht een object van Bedrijf en stuur dit door naar de add methode van Bedrijfservice.
     * @param bedrijf
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Integer create(Bedrijf bedrijf) {
        return service.add(bedrijf);

    }

    /**
     * Methode voor het wijzigen van een Bedrijf. aanroepbaar via een put request.
     * verwacht een object van Bedrijf en een id en stuur dit door naar de update methode van Bedrijfservice.
     * @param id
     * @param bedrijf
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@PathParam("id") int id, Bedrijf bedrijf) {
        bedrijf.setId(id);
        service.update(id,bedrijf);
    }

    /**
     * Methode voor het deactiveren van een Bedrijf. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van Bedrijfservice.
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }

    /**
     * Methode voor het activeren van een Bedrijf. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de activeer methode van Bedrijfservice.
     * @param id
     */
    @DELETE
    @Path("/activeer/{id}")
    @RolesAllowed("ADMIN")
    public void activeer(@PathParam("id") int id) {
        service.activeer(id);
    }

}