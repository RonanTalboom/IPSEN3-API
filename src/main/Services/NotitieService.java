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
       return dao.select();
    }

    /**
     * @param notitieid
     */
    public Notitie get(int notitieid) {
        return dao.select(notitieid);
    }

    /**
     * @param notitie
     */
    public void add(Notitie notitie) {
        dao.insert(notitie);
    }

    /**
     * @param id
     * @param notitie
     */
    public void update(int id, Notitie notitie) {
        dao.update(notitie);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }


    public Collection<Notitie> getKlantNotitie(int id){
        return dao.selectbyKlant(id);
    }

    public Collection<Notitie> getBedrijfNotitie(int id){
        return dao.selectbyBedrijf(id);
    }

}