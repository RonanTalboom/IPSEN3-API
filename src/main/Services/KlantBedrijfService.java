package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Persistence.KlantBedrijfDOA;

import java.util.Collection;

/**
 *
 */
@Singleton
public class KlantBedrijfService {
    /**
     *
     */
    public final KlantBedrijfDOA dao;

    /**
     * Default constructor
     */
    @Inject
    public KlantBedrijfService(KlantBedrijfDOA dao) {
        this.dao = dao;
    }


    /**
     *
     */
//    public BedrijfResource 1;

    /**
     *
     */
//    public KlantBedrijfDOA 1;

    /**
     * @param dao
     */
//    public void BedrijfService(KlantBedrijfDOA dao) {
//        this.dao = dao;
//    }

    /**
     *
     */
    public Collection<Klant> getAll(int id) {
        Bedrijf bedrijf =  new Bedrijf();
        bedrijf.setId(id);
        dao.setBedrijf(bedrijf);
        dao.selectWerkzameKlanten();
        return dao.getWerkzameKlanten();
    }

//        /**
//         * @param id
//         */
//    public Bedrijf get(int id) {
//        dao.select();
//        for (Bedrijf bedrijf: dao.getObserversBedrijven()){
//            if (bedrijf.getId() == (id)) {
//                return bedrijf;
//            }
//        }
//        return null;
//    }
//
//
//    /**
//     * @param bedrijf
//     */
//    public void add(Bedrijf bedrijf) {
//        dao.setBedrijf(bedrijf);
//        dao.insert();
//    }
//
//    /**
//     * @param id
//     * @param bedrijf
//     */
//    public void update(int id, Bedrijf bedrijf) {
//        dao.setBedrijf(bedrijf);
//        dao.update(id);
//    }
//
    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

}