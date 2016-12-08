package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
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
       return null;
    }

    /**
     * @param notitieid
     */
    public void get(int notitieid) {
        // TODO implement here
    }

    /**
     * @param notitie
     */
    public void add(Notitie notitie, int klantid, int bedrijfid) {
        // TODO implement here
    }

    /**
     * @param id
     * @param notitie
     */
    public void update(int id, Notitie notitie) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void delete(int id) {
        // TODO implement here
    }


    public Collection<Notitie> getKlantNotitie(Klant klant){
        dao.setKlant(klant);
        dao.filterKlantNotitie();
    }

}