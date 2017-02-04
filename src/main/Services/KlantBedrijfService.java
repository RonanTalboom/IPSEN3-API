package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Model.KlantBedrijf;
import main.Persistence.KlantBedrijfDAO;

import java.util.Collection;

/**
 *
 */
@Singleton
public class KlantBedrijfService {
    /**
     *
     */
    public final KlantBedrijfDAO dao;

    /**
     * Default constructor
     */
    @Inject
    public KlantBedrijfService(KlantBedrijfDAO dao) {
        this.dao = dao;
    }

    /**
     *
     */
    public Collection<Klant> getAll(int id) {
        return dao.selectWerkzameKlanten(id);
    }


    public Collection<Klant> getOverigeAll(int id) {
        return dao.selectOverigeKlanten(id);
    }

    public Collection<Bedrijf> getOverigeBedrijvenAll(int id) {
        return dao.selectOverigebedrijven(id );
    }

    public Collection<Bedrijf> getAllBedrijven(int id) {
        return dao.selectWerkzameBedrijven(id);

    }

    /**
     * @param
     */
    public void add(int bedrijfID,int klantID) {
        KlantBedrijf klantBedrijf = new KlantBedrijf();
        klantBedrijf.setKlantId(klantID);
        klantBedrijf.setBedrijfId(bedrijfID);
        dao.insert(klantBedrijf);
    }

    /**
     * @param
     */
    public void delete(int klantId, int bedrijfId) {
        dao.delete(klantId,bedrijfId);
    }
}