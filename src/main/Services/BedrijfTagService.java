package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.BedrijfTag;
import main.Persistence.BedrijfTagDAO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Dit is de BedrijfTag Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
public class BedrijfTagService {

    /**
     * Dit is een Object van BedrijfTagDOA. Dit is nodig om de communiceren met de database.
     */
    private final BedrijfTagDAO dao;

    /**
     * Constructor van BedrijfTagService
     * @param dao geinjecteerd in de klasse.
     */
    @Inject
    public BedrijfTagService(BedrijfTagDAO dao) {
        this.dao = dao;
    }

    /**
     * /**
     * Methode bedoeldt om alle bedrijfTags gekoppeld aan een bedrijf uit de database op te halen.
     * @param bedrijfId van de desbetreffende bedrijf.
     * @return Integer collection van tagID's.
     */
    public Collection<Integer> get(int bedrijfId) {
        return dao.selectByBedrijf(bedrijfId).stream().map(BedrijfTag::getTagId).collect(Collectors.toList());
    }

    /**
     * Methode bedoeldt voor het toevoegen van bedrijfTags in de database.
     * @param tagIDs Integer collection van tagID's.
     * @param bedrijfId van de desbetreffende bedrijf.
     */
    public void add(Collection<Integer> tagIDs, int bedrijfId) {
        delete(bedrijfId);
        for (Integer tagID: tagIDs) {
            BedrijfTag bedrijfTag = new BedrijfTag();
            bedrijfTag.setBedrijfId(bedrijfId);
            bedrijfTag.setTagId(tagID);
            dao.insert(bedrijfTag);
        }
    }

    /**
     * Methode bedoeldt voor het verwijderen van bedrijfTags die gekoppeld zijn het betreffende bedrijf in de database.
     * @param bedrijfId van de desbetreffende bedrijf.
     */
    public void delete(int bedrijfId) {
        dao.delete(bedrijfId);
    }

}