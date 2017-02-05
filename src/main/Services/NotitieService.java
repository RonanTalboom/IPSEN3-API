package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Model.Notitie;
import main.Persistence.NotitieDAO;

import java.util.*;


/**
 * Dit is de notitie Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Ronan Talboom
 * @version 1.0, Januari 2017
 */
@Singleton
public class NotitieService {


    /**
     *Dit is een Object van NotitieDOA.Deze wordt gebruikt om data doorsturen naar de dao zodat de dao
     * kan communiceren met de database.
     */
    public NotitieDAO dao;

    /**
     * Constructor van notitieService. in de Inject wordt de NotitieDAO gezet.
     * @param dao
     */
    @Inject
    public void NotitieService(NotitieDAO dao) {
        this.dao = dao;
    }

    /**
     * Alle notities ophalen.
     * @return
     */
    public Collection<Notitie> getAll() {
       return dao.select();
    }


    /**
     *  enkele notitie ophalen
     * @param notitieid
     * @return notitie
     */
    public Notitie get(int notitieid) {
        return dao.select(notitieid);
    }

    /**
     * een notitie toevoegen
     * @param notitie
     */
    public void add(Notitie notitie) {
        dao.insert(notitie);
    }

    /**
     * een notitie updaten
     * @param id
     * @param notitie
     */
    public void update(int id, Notitie notitie) {
        dao.update(notitie);
    }

    /**
     * een notitie verwijderen.
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }


    /**
     * notities ophalen die bij een klant horen
     * @param id
     * @return notities
     */
    public Collection<Notitie> getKlantNotitie(int id){
        return dao.selectbyKlant(id);
    }

    /**
     *  notities ophalen die bij een bedrijf horen.
     * @param id
     * @return notities
     */
    public Collection<Notitie> getBedrijfNotitie(int id){
        return dao.selectbyBedrijf(id);
    }

}