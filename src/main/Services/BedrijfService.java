package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Persistence.BedrijfDAO;

import java.util.Collection;

/**
 *
 */
@Singleton
public class BedrijfService {
    /**
     *
     */
    public final BedrijfDAO dao;

    /**
     * Default constructor
     */
    @Inject
    public BedrijfService(BedrijfDAO dao) {
        this.dao = dao;
    }


    /**
     *
     */
//    public BedrijfResource 1;

    /**
     *
     */
//    public BedrijfDAO 1;

    /**
     * @param dao
     */
//    public void BedrijfService(BedrijfDAO dao) {
//        this.dao = dao;
//    }

    /**
     *
     */
    public Collection<Bedrijf> getAll() {
        dao.select();
        return dao.getObserversBedrijven();
    }

        /**
         * @param id
         */
    public Bedrijf get(int id) {
        dao.select();
        for (Bedrijf bedrijf: dao.getObserversBedrijven()){
            if (bedrijf.getId() == (id)) {
                return bedrijf;
            }
        }
        return null;
    }


    /**
     * @param bedrijf
     */
    public Integer add(Bedrijf bedrijf) {
        dao.setBedrijf(bedrijf);
        dao.insert();

        return dao.getID();
    }

    /**
     * @param id
     * @param bedrijf
     */
    public void update(int id, Bedrijf bedrijf) {
        dao.setBedrijf(bedrijf);
        dao.update(id);

    }

    /**
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

    public void activeer(int id) {
        dao.activeer(id);
    }
}