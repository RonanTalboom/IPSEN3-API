package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.KlantTag;
import main.Persistence.KlantTagDAO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Dit is de KlantTag Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
public class KlantTagService {

    /**
     * Dit is een Object van KlantTagDOA. Dit is nodig om de communiceren met de database.
     */
    private final KlantTagDAO dao;

    /**
     * Constructor van KlantTagService
     * @param dao geinjecteerd in de klasse.
     */
    @Inject
    public KlantTagService(KlantTagDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om alle klantTags gekoppeld aan een klant uit de database op te halen.
     * @param klantId van de desbetreffende klant.
     * @return Integer collection van tagID's.
     */
    public Collection<Integer> get(int klantId) {
        return dao.selectByKlant(klantId).stream().map(KlantTag::getTagId).collect(Collectors.toList());
    }

    /**
     *
     * Methode bedoeldt voor het toevoegen van KlantTags in de database.
     * @param tagIDs Integer collection van tagID's.
     * @param klantId van de desbetreffende klant.
     */
    public void add(Collection<Integer> tagIDs, int klantId) {
        delete(klantId);
        for (Integer tagID: tagIDs) {
            KlantTag klantTag = new KlantTag();
            klantTag.setKlantId(klantId);
            klantTag.setTagId(tagID);
            dao.insert(klantTag);
        }
    }

    /**
     * Methode bedoeldt voor het verwijderen van KlantTags die gekoppeld zijn het betreffende klant in de database.
     * @param klantId van de desbetreffende klant.
     */
    public void delete(int klantId) {
        dao.delete(klantId);
    }

}