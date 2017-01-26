package main.Services;

import com.google.inject.Singleton;
import main.Model.Bestand;
import main.Model.Klant;
import main.Persistence.KlantFileDAO;
import main.Resource.KlantFileResource;

import java.io.File;
import java.util.Collection;


/**
 *
 */
@Singleton
public class KlantFileService {

    /**
     * Default constructor
     */
    /**
     *
     */
    public KlantFileDAO dao;


    /**
     *
     */

    /**
     * @param dao
     */
    public  KlantFileService(KlantFileDAO dao) {
        this.dao = dao;
    }
    public Collection<Bestand> getAll() {
        dao.select();
        return dao.getObserversBestand();
    }
    /**
     * @param id
     */
    public Bestand get(int id) {
       dao.select();
        for(Bestand bestand: dao.getObserversBestand()){
            if(bestand.getBestand_Id() == id){
                return bestand;
            }
        }
        return null;
    }

    /**
     * @param bestand
     * @param
     */
    public void add(Bestand bestand) {
        dao.setBestand(bestand);
        dao.insert();
    }

    /**
     * @param klantid
     * @param fileid
     */
    public void delete(int klantid, int fileid) {
        // TODO implement here
    }

}