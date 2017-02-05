package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Klant;
import main.Persistence.KlantDAO;

import java.util.*;

/**
 * Dit is de  KlantService. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Mike Uan
 * @version 1.0, Januari 2017
 */
@Singleton
public class KlantService {

    /**
     * Dit is een Object van klantDao. Dit is nodig om de communiceren met de database.
     */
    private KlantDAO dao;

    /**
     * Constructor van KlantFileService
     * @param dao geinjecteerd in de klasse.
     */
    @Inject
    public KlantService(KlantDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om alle klanten uit de database op te halen.
     * @return collection van klanten.
     */
    public Collection<Klant> getAll() {
        return dao.select();
    }

    /**
     * Methode bedoeldt om een klant uit de database op te halen.
     * @return een klant.
     */
    public Klant get(int id) {
        return dao.select(id);
    }

    /**
     * Methode bedoeldt voor het toevoegen van een Klant in de database.
     * @param klant object met alle nodige waardes.
     */
    public Integer add(Klant klant) {
        return dao.insert(klant);
    }

    /**
     * Methode bedoeldt voor het wijzigen van een Klant in de database.
     * @param klant object met alle nodige waardes.
     */
    public void update( Klant klant) {
        dao.update(klant);
    }

    /**
     * Methode bedoeldt voor het deactiveren van een Klant in de database.
     * @param id van de desbetreffende klant.
     */
    public void delete(int id) {
       dao.delete(id);
    }
    /**
     * Methode bedoeldt voor het activeren van een Klant in de database.
     * @param id van de desbetreffende klant.
     */
    public void activeer(int id){
        dao.activeer(id);
    }

}