package main.Services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import main.Model.Tag;
import main.Persistence.TagDAO;

import java.util.Collection;

/**
 * Dit is de Tag Service. Dit klasse is verantwoordlijk voor het communiceren met de DOA.
 *
 * @author Shaban Jama
 * @version 1.0, Januari 2017
 */
@Singleton
public class TagService {

    /**
     * Dit is een Object van TagDOA. Dit is nodig om de communiceren met de database.
     */
    private final TagDAO dao;

    /**
     * Constructor van TagService
     * @param dao geinjecteerd in de klasse.
     */
    @Inject
    public TagService(TagDAO dao) {
        this.dao = dao;
    }

    /**
     * Methode bedoeldt om alle tags uit de database op te halen.
     * @return collection van tags.
     */
    public Collection<Tag> getAll() {
        return dao.select();
    }

    /**
     * Methode bedoeldt voor het toevoegen van een Tag in de database.
     * @param tag object met alle nodige waardes.
     */
    public void add(Tag tag) {
        dao.insert(tag);
    }

    /**
     * Methode bedoeldt voor het wijzigen van een Tag in de database.
     * @param tag object met alle nodige waardes.
     */
    public void update(Tag tag) {
        dao.update(tag);
    }

    /**
     * Methode bedoeldt voor het verwijderen van een Tag in de database.
     * @param id van de desbetreffende tag.
     */
    public void delete(int id) {
        dao.delete(id);
    }

}