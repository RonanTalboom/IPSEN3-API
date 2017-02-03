package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Beheerder;
import main.Model.Klant;
import main.Persistence.KlantDAO;

import java.util.*;

/**
 *
 */
@Singleton
public class KlantService {


    /**
     *
     */
    public KlantDAO dao;



    /**
     * @param dao
     */
    @Inject
    public KlantService(KlantDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Klant> getAll() {
        return dao.select();
    }

    /**
     * @param id
     */
    public Klant get(int id) {
        return dao.select(id);
    }

    /**
     * @param klant
     */
    public Integer add(Klant klant) {
        return dao.insert(klant);
    }

    /**
     * @param id
     * @param klant
     */
    public void update( int id, Klant klant) {
        dao.update(klant);
    }

    /**
     * @param id
     */
    public void delete(int id) {
       dao.delete(id);
    }


}