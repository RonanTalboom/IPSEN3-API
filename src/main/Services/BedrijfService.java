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
    public Collection<Bedrijf> getAll() {
        return dao.select();
    }

        /**
         * @param id
         */
    public Bedrijf get(int id) {
        return dao.select(id);
    }


    /**
     * @param bedrijf
     */
    public Integer add(Bedrijf bedrijf) {
        return dao.insert(bedrijf);
    }

    /**
     * @param id
     * @param bedrijf
     */
    public void update(Bedrijf bedrijf) {
        dao.update(bedrijf);

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