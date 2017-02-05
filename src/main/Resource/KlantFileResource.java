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
 * Dit is de KlantFile Resource. Deze klasse is verantwoordlijk voor het afhandelen van http request.
 *
 * @author Mike Yan
 * @version 1.0, Januari 2017
 */
@Singleton
@Path("/klantBestand")
@Produces(MediaType.APPLICATION_JSON)
public class KlantFileResource {



    /**
     * Dit is een Object van klantFileService. Dit is nodig om de request aftehandelen.
     */
    public KlantFileService klantFileService;

    /**
     * constructor van KlantFileResource
     * @param service geinjecteerd in de klasse.
     */
    @Inject
    public KlantFileResource(KlantFileService service) {
        this.klantFileService = service;
    }

    /**
     * Methode voor het ophalen van een bestanden. aanroepbaar via een get request.
     * Stuurt een collectie van bestanden objecten terug.
     * @return collection van bestanden.
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Bestand> retrieveAll(@PathParam("id") int id) {
        return klantFileService.get(id);
    }


    /**
     * Methode voor het verwijderen van een Bestand. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van KlantFileService.
     * @param id van de desbetreffende bestand.
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        klantFileService.delete(id);
    }

    /**
     * Methode voor het toevoegen van een Bestand. aanroepbaar via een post request.
     * verwacht een object van Bestand en stuur dit door naar de add methode van klantFileService.
     * @param bestand object met alle nodige waardes.
     */
    @POST
    @RolesAllowed("BEHEERDER")
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Bestand bestand)
    {
      klantFileService.add(bestand);
    }


}