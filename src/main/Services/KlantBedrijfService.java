package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Model.Klant;
import main.Model.KlantBedrijf;
import main.Persistence.KlantBedrijfDAO;

import java.util.Collection;

/**
 * Dit is de KlantBedrijf Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 * @author Mohamed El Baze
 * @version 1.0, Januari 2017
 */
@Singleton
public class KlantBedrijfService {
    /**
     * Dit is een Object van KlantBedrijfDOA. Dit is nodig om de communiceren met de database.
     */
    public final KlantBedrijfDAO dao;

    /**
     * Constructor van KlantBedrijfService
     * @param dao
     */
    @Inject
    public KlantBedrijfService(KlantBedrijfDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om een alle gekoppelde klanten bij een bedrijf uit de database op te halen.
     * @param id
     * @return
     */
    public Collection<Klant> getAll(int id) {
        return dao.selectWerkzameKlanten(id);
    }


    /**
     * Methode bedoeldt om een alle niet gekoppelde klanten bij een bedrijf uit de database op te halen.
     * @param id
     * @return
     */
    public Collection<Klant> getOverigeAll(int id) {
        return dao.selectOverigeKlanten(id);
    }

    /**
     * Methode bedoeldt om een alle gekoppelde bedrijven bij een klant uit de database op te halen.
     * @param id
     * @return
     */
    public Collection<Bedrijf> getOverigeBedrijvenAll(int id) {
        return dao.selectOverigebedrijven(id );
    }

    /**
     * Methode bedoeldt om een alle niet gekoppelde bedrijven bij een klant uit de database op te halen.
     * @param id
     * @return
     */
    public Collection<Bedrijf> getAllBedrijven(int id) {
        return dao.selectWerkzameBedrijven(id);

    }

    /**
     * Methode bedoeldt om een een klant/bedrijf te koppelen in de database op te halen.
     * @param bedrijfID
     * @param klantID
     */
    public void add(int bedrijfID,int klantID) {
        KlantBedrijf klantBedrijf = new KlantBedrijf();
        klantBedrijf.setKlantId(klantID);
        klantBedrijf.setBedrijfId(bedrijfID);
        dao.insert(klantBedrijf);
    }

    /**
     * Methode bedoeldt om een een klant/bedrijf te ontkoppelen in de database op te halen.
     * @param klantId
     * @param bedrijfId
     */
    public void delete(int klantId, int bedrijfId) {
        dao.delete(klantId,bedrijfId);
    }
}