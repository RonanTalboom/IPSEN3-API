package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.ObservableList;
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
    @Inject
    public  KlantFileService(KlantFileDAO dao) {
        this.dao = dao;
    }
    public Collection<Bestand> getAll(Klant klant) {
        dao.select();
        dao.setKlant(klant);
        return dao.getObserversBestand();
    }
//    /**
//     * @param id
//     */
//    public Bestand get(int id) {
//       dao.select();
//        for(Bestand bestand: dao.getObserversBestand()){
//            if(bestand.getBestand_Id() == id){
//                return bestand;
//            }
//        }
//        return null;
//    }

    public ObservableList<Bestand> get(int klantid) {
        Klant klant = new Klant();
        klant.setId(klantid);
        dao.setKlant(klant);
        dao.select();

        System.out.println("sangam"+ dao.getObserversBestand());
        return dao.getObserversBestand();

    }





    /**
     * @param
     */
    public void add(Bestand bestand) {
        Klant klant = new Klant();
        klant.setId(bestand.getKlant_Id());
        dao.setKlant(klant);
        dao.setBestand(bestand);
        dao.insert();
    }

    /**
     * @param id
     */
    public void delete( int id) {
        dao.delete(id);
    }

    public void downloadFile(int id){
        dao.downloadFile(id);
    }

}