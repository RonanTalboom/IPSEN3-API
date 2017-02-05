package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bedrijf;
import main.Persistence.BedrijfDAO;

import java.util.Collection;

/**
 * Dit is de Bedrijf Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 * @author Mohamed El Baze
 * @version 1.0, Januari 2017
 */
@Singleton
public class BedrijfService {
    /**
     * Dit is een Object van BedrijfDOA. Dit is nodig om de communiceren met de database.
     */
    public final BedrijfDAO dao;

    /**
     * Constructor van BedrijfService
     * @param dao
     */
    @Inject
    public BedrijfService(BedrijfDAO dao) {
        this.dao = dao;
    }


    /**
     *Methode bedoeldt om alle Bedrijven uit de database op te halen.
     *@return collection van Bedrijf.
     */
    public Collection<Bedrijf> getAll() {
        return dao.select();
    }

        /**
         * Methode bedoeldt om een speciefieke bedrijf uit de database op te halen.
         * @param id
         * @return
         */
    public Bedrijf get(int id) {
        return dao.select(id);
    }


    /**
     * Methode bedoeldt voor het toevoegen van een Bedrijf in de database.
     * @param bedrijf
     * @return
     */
    public Integer add(Bedrijf bedrijf) {
        return dao.insert(bedrijf);
    }

    /**
     * Methode bedoeldt voor het wijzigen van een Bedrijf in de database.
     * @param id
     * @param bedrijf
     */
    public void update(int id, Bedrijf bedrijf) {
        dao.update(bedrijf);

    }

    /**
     * Methode bedoeldt voor het deactiveren van een Bedrijf in de database.
     * @param id
     */
    public void delete(int id) {
        dao.delete(id);
    }

    /**
     * Methode bedoeldt voor het activeren van een Bedrijf in de database.
     * @param id
     */
    public void activeer(int id) {
        dao.activeer(id);
    }
}