package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Bestand;
import main.Persistence.KlantFileDAO;
import java.util.Collection;


/**
 * Dit is de  KlantFileService. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Mike Uan
 * @version 1.0, Januari 2017
 */
@Singleton
public class KlantFileService {
    /**
     * Dit is een Object van klantFileDao. Dit is nodig om de communiceren met de database.
     */
    private KlantFileDAO dao;

    /**
     * Constructor van KlantFileService
     * @param dao geinjecteerd in de klasse.
     */
    @Inject
    public  KlantFileService(KlantFileDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om alle bestanden uit de database op te halen.
     * @return collection van Bestand.
     */
    public Collection<Bestand> get(int klantid) {
        return dao.selectByKlant(klantid);

    }

    /**
     * Methode bedoeldt voor het toevoegen van een Bestand in de database.
     * @param bestand object met alle nodige waardes.
     */
    public void add(Bestand bestand) {
        dao.insert(bestand);
    }

    /**
     * Methode bedoeldt voor het verwijderen van een Bestand in de database.
     * @param id van de desbetreffende bestand.
     */
    public void delete( int id) {
        dao.delete(id);
    }


}