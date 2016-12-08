package main.Services;

import main.Model.Klant;
import main.Persistence.KlantDAO;

import java.util.*;

/**
 *
 */
public class KlantService {


    /**
     *
     */
    public KlantDAO dao;



    /**
     * @param dao
     */
    public KlantService(KlantDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Klant> getAll() {
        dao.select();
        return dao.getKlantlist();
    }

    /**
     * @param id
     */
    public Klant get(int id) {
        dao.select();
        Klant rklant = new Klant();
        for(Klant klant: dao.getKlantlist()) {
            if(klant.getId() == (id)){
                rklant = klant;
            }

        }
        return  rklant.equals(null)
                ? null
                : rklant;

    }

    /**
     * @param klant
     */
    public void add(Klant klant) {
        dao.setKlant(klant);
        dao.insert();
    }

    /**
     * @param authenticator
     * @param id
     * @param klant
     */
    public void update(Klant authenticator, int id, Klant klant) {
        dao.insert();
        dao.setKlant(klant);
    }

    /**
     * @param id
     */
    public void delete(int id) {
        // TODO implement here
    }

}