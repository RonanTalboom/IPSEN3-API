package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Model.Notitie;
import main.Persistence.NotitieDAO;

import java.util.*;

/**
 *
 */
@Singleton
public class NotitieService {


    /**
     *
     */
    public NotitieDAO dao;

    /**
     * @param dao
     */
    @Inject
    public void NotitieService(NotitieDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Notitie> getAll() {
       dao.select();
       return dao.getNotities();
    }

    /**
     * @param notitieid
     */
    public Notitie get(int notitieid) {
        dao.select();
        return dao.getNotitie();
    }

    /**
     * @param notitie
     */
    public void add(Notitie notitie) {
        dao.setNotitie(notitie);
        dao.insert();
    }

    /**
     * @param id
     * @param notitie
     */
    public void update(int id, Notitie notitie) {
        dao.setNotitie(notitie);
        dao.update(id);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }


    public Collection<Notitie> getKlantNotitie(int id){
        Klant klant = new Klant();
        klant.setId(id);
        dao.setKlant(klant);
        dao.filterKlantNotitie();
        return dao.getNotities();
    }

    public Collection<Notitie> getBedrijfNotitie(int id){
        Bedrijf bedrijf = new Bedrijf();
        bedrijf.setId(id);
        dao.setBedrijf(bedrijf);
        dao.filterBedrijfNotitie();
        return dao.getNotities();
    }

}