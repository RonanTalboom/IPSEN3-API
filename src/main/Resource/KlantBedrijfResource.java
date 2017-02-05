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
 * Dit is de KlantBedrijfResource. Dit klasse is verantwoordlijk voor het afhandelen van http request voor KlantBedrijf.
 * @author Mohamed El Baze
 * @version 1.0, Januari 2017
 */
@Singleton
@Path("/bedrijven/klanten")
@Produces(MediaType.APPLICATION_JSON)
public class KlantBedrijfResource {

    /**
     * Dit is een Object van KlantBedrijfService. Dit is nodig om de request aftehandelen.
     */
    private final KlantBedrijfService service;

    /**
     * Constructor van KlantBedrijfResource
     * @param service
     */
    @Inject
    public KlantBedrijfResource(KlantBedrijfService service) {
        this.service = service;
    }

    /**
     * Methode voor het ophalen van gekoppelde klanten bij een bedrijf. aanroepbaar via een get request.
     * Stuurt een collectie van Klant gegevens terug die gekoppeld zijn aan de bedrijfId.
     * @param id
     * @return
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Klant> retrieveAll(@PathParam("id") int id) {
        return service.getAll(id);
    }

    /**
     * Methode voor het ophalen van niet gekoppelde klanten bij een bedrijf. aanroepbaar via een get request.
     * Stuurt een collectie van Klant gegevens terug die niet gekoppeld zijn aan de bedrijfId.
     * @param id
     * @return
     */
    @GET
    @Path("/overige/{id}")
    @RolesAllowed("BEHEERDER")
    public Collection<Klant> retrieveOverigeAll(@PathParam("id") int id) {
        return service.getOverigeAll(id);
    }


    /**
     * Methode voor het ophalen van gekoppelde bedrijven bij een klant. aanroepbaar via een get request.
     * Stuurt een collectie van bedrijf gegevens terug die gekoppeld zijn aan de bedrijfId.
     * @param id
     * @return
     */
    @GET
    @Path("/gekoppeldebedrijven/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Bedrijf> retrieveAllBedrijven(@PathParam("id") int id) {
        return service.getAllBedrijven(id);
    }

    /**
     * Methode voor het ophalen van niet gekoppelde bedrijven bij een klant. aanroepbaar via een get request.
     * Stuurt een collectie van bedrijf gegevens terug die niet gekoppeld zijn aan de bedrijfId.
     * @param id
     * @return
     */
    @GET
    @Path("/overigebedrijven/{id}")
    @RolesAllowed("BEHEERDER")
    public Collection<Bedrijf> retrieveOverigeAllBedrijven(@PathParam("id") int id) {
        return service.getOverigeBedrijvenAll(id);
    }

    /**
     * Methode voor het koppelen van klant/bedrijf. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van KlantBedrijfservice.
     * @param bedrijfID
     * @param klantID
     */
    @PUT
    @Path("/{bedrijfid}/{klantid}")
    @RolesAllowed("BEHEERDER")
    public void insert(@PathParam("bedrijfid") int bedrijfID,@PathParam("klantid") int klantID) {
        service.add(bedrijfID,klantID);
    }

    /**
     * Methode voor het ontkopplen van klant/bedrijf. aanroepbaar via een delete request.
     * verwacht een id en stuur dit door naar de delete methode van KlantBedrijfservice.
     * @param bedrijfid
     * @param klantid
     */
    @DELETE
    @Path("/{bedrijfid}/{klantid}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("bedrijfid") int bedrijfid,@PathParam("klantid") int klantid) {
        service.delete( klantid, bedrijfid);
    }

}