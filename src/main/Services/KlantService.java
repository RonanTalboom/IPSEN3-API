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
        dao.select();
        return dao.getKlantlist();
    }

    /**
     * @param id
     */
    public Klant get(int id) {
        dao.select();
        for(Klant klant: dao.getKlantlist()) {
            if(klant.getId() == (id)){
                return klant;
            }

        }
        return null;

    }

    /**
     * @param klant
     */
    public void add(Klant klant) {
        dao.setKlant(klant);
        dao.insert();
    }

    public Integer getklant(Klant klant){
        dao.setKlant(klant);
        return dao.getID();
    }

    /**
     * @param id
     * @param klant
     */
    public void update( int id, Klant klant) {
        dao.setKlant(klant);
        klant.setId(id);
        dao.update(id);
    }

    /**
     * @param id
     */
    public void delete(int id) {
       dao.delete(id);
    }

    public void activeer(int id){
        dao.activeer(id);
    }
//    public Klant me(Klant klant){
//        dao.select();
//        for(Klant k : dao.getKlantlist()){
//            if(k.getEmail().equals(klant.getEmail()))
//                return k;
//        }
//        return null;
//    }

}