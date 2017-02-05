package main.Resource;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.inject.Singleton;
import main.Model.Notitie;
import main.Services.NotitieService;
import main.View;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * NotitieResource handelt alle http request af die te maken hebben met de notities.
 * deze klassen is een singleton.
 */
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Path("/notities")
public class NotitieResource {


    /**
     * dit is de service object. deze is nog om met de services te praten.
     *
     */
    public NotitieService service;

    /**
     * Deze methode is de constructor hierin wordt de service aangegeven door middel van inject.
     * @param service
     */
    @Inject
    public void NotitieResource(NotitieService service) {
        this.service = service;
    }

    /**
     * deze methode wordt gebruikt om alle notities op te halen.
     * Alleen beheerders of hoger mag deze aanroepen.
     * deze methode wordt aangeroepen doormiddel van een get request
     * @return notities
     */
    @GET
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> retrieveAll() {
        return service.getAll();
    }

    /**
     * deze methode is verantwoordelijk om 1 notitie op te halen.
     * Alleen beheerders of hoger mag deze aanroepen
     * deze methode wordt aangeroepen door een get request
     * @param id
     * @return notitie
     */
    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @RolesAllowed("BEHEERDER")
    public Notitie retrieve(int id) {
        return service.get(id);
    }

    /**
     * deze methode is verantwoordelijk om een nieuwe notitie aan te maken
     * Alleen beheerders of hoger mag deze aanroepen
     * deze methode wordt gedaan met een Post request
     * @param notitie
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void create(Notitie notitie) {
        service.add(notitie);
    }

    /**
     * één notitie bewerken
     * Alleen beheerders of hoger mag deze aanroepen
     * deze methode wordt met een put request aangeroepen. hierbij moet een id en een JSON notitie
     * worden meegegeven
     * @param id
     * @param notitie
     */
    @PUT
    @Path("/{id}")
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public void update(@PathParam("id") int id, Notitie notitie) {
        service.update(id,notitie);
    }

    /**
     * Deze methode is verantwoordelijk voor het verwijderen van een notitie
     * Alleen beheerders of hoger mag deze aanroepen
     * deze methode wordt aangeroepen met een Delete request. hierbij moet de id worden meegegeven.
     * @param id
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("BEHEERDER")
    public void delete(@PathParam("id") int id) {
        service.delete(id);
    }


    /**
     * Deze methode is verantwoordelijk voor het ophalen van notities die bij een klant horen.
     * Alleen beheerders of hoger mag deze aanroepen
     * voor het gebruik van deze methode moet er een id mee gegeven worden en JSON data. dit gebeurt
     * alleemaal doormiddel van een Get request.
     * @param id
     * @return Notities
     */
    @GET
    @Path("/klant/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> klantNotitie(@PathParam("id") int id) {

        return service.getKlantNotitie(id);
    }

    /**
     * Deze methode is verantwoordelijk voor het ophalen van notities die bij een bedrijf horen
     * Alleen beheerders of hoger mag deze aanroepen
     * Deze methode wordt aangeroepen doormiddel van een get request.
     * @param id
     * @return notities
     */
    @GET
    @Path("/bedrijf/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @JsonView(View.Protected.class)
    @RolesAllowed("BEHEERDER")
    public Collection<Notitie> klantBedrijf(@PathParam("id")int id) {
        return service.getBedrijfNotitie(id);
    }

}